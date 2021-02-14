package com.example.chitchat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class OneToOne extends AppCompatActivity {

    private static final String activityName = "OneToOne";
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference();
    private static final DatabaseReference myErrorRef = database.getReference().child("errors").child(activityName);
    private Error_class error_class = new Error_class();

    private mydatabase db = new mydatabase(this);

    private ListView Lst_Chat;
    private EditText txt_msg;
    private ArrayList<msg_class> onlineMessages = new ArrayList<>();
    private ArrayList<msg_class> offlineMessages = new ArrayList<>();
    private ArrayList<String> profile = new ArrayList<>();

    private LinearLayout layout_translateSettings;
    private CheckBox cb_enableT;
    private Spinner spnr_from, spnr_to;
    private String lastmsg, chatRoomId, userId, partnerId, partnerName, sourceLanguage, targetLanguage;
    private String[] lang;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_one);

        try {
            Lst_Chat = findViewById(R.id.Lst_Chat);
            txt_msg = findViewById(R.id.txt_msg);
            layout_translateSettings = findViewById(R.id.layout_translateSettings);
            cb_enableT = findViewById(R.id.cb_enableT);
            spnr_to = findViewById(R.id.spnr_to);
            spnr_from = findViewById(R.id.spnr_from);
            TextView txt_partnerName = findViewById(R.id.txt_partnerName);

            lang = getResources().getStringArray(R.array.lang);

            userId = db.getString("SELECT user_id FROM user ORDER BY id DESC LIMIT 1");
            String qry = "SELECT userName, userGender FROM profile ORDER BY id DESC LIMIT 1";
            profile = db.getProfileChat(qry);
            partnerId = Objects.requireNonNull(getIntent().getExtras()).getString("id");
            partnerName = Objects.requireNonNull(getIntent().getExtras()).getString("name");

            txt_partnerName.setText(partnerName);

            if (userId.compareTo(partnerId) >= 0)
                chatRoomId = userId + "-" + partnerId;
            else
                chatRoomId = partnerId + "-" + userId;

            lastmsg = db.getString("SELECT msgId FROM chat WHERE groupNumber ='" + chatRoomId + "' ORDER BY id DESC LIMIT 1");

            getData();
            getOnlineMsg();

        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getData(){
        try {
            String qry = "SELECT * FROM chat WHERE groupNumber ='" + chatRoomId + "'";
            offlineMessages = db.getMsg(qry);
            if (!offlineMessages.get(0).empty.matches("1"))
                viewData();
        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void viewData(){
        try {
            Listadapter listadapter = new Listadapter();
            Lst_Chat.setAdapter(listadapter);
            Lst_Chat.setSelection(listadapter.getCount() - 1);
        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getOnlineMsg(){
        try {
            myRef.child("oneToOne").child(chatRoomId).orderByKey().startAt(lastmsg).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren())
                        onlineMessages.add(singleSnapshot.getValue(msg_class.class));

                    if (onlineMessages.size() > 0)
                        saveLocalDB();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }

    }

    private void saveLocalDB(){
        try {
            String userName, userGender, userId, msgId, msg;
            boolean result;
            for (int i = 0; i < onlineMessages.size(); i++){
                msgId = onlineMessages.get(i).msgId;
                int id = db.getInteger("SELECT id FROM chat WHERE msgId ='" + msgId + "'");
                if (id == -1){
                    userId = onlineMessages.get(i).userId;
                    userName = onlineMessages.get(i).userName;
                    userGender = onlineMessages.get(i).userGender;
                    msg = onlineMessages.get(i).msg;
                    result = db.insertMsg(msgId, userName, userId, msg, userGender, chatRoomId);
                    if (!result)
                        Toast.makeText(this, getResources().getString(R.string.ErrorSave), Toast.LENGTH_LONG).show();
                    String qry = "REPLACE INTO partners(roomId, partner) VALUES('" + chatRoomId + "','" + partnerName + "')";
                    db.insertPartner(qry);
                }
            }
            onlineMessages.clear();
            getData();
        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    private void translate(){
        try {

        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    public void translationS(View view) {
        try {

        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    public void back(View view) {
        try {

        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    public void msgSend(View view) {
        try {

        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()) {
                if (ste.getClassName().contains(activityName))
                    break;
                i++;
            }
            String lineError = e.getStackTrace()[i].getLineNumber() + "";
            String msg = e.getMessage();
            error_class.sendError(myErrorRef, lineError, msg, functionName);
        }
    }

    class Listadapter extends BaseAdapter {
        @Override
        public int getCount() {

            return offlineMessages.size();
        }

        @Override
        public Object getItem(int position) {

            return offlineMessages.get(position);
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}