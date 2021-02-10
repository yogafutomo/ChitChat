package com.example.chitchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Group extends AppCompatActivity {

    private static final String activityName = "Group";
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference();
    private static final DatabaseReference myErrorRef = database.getReference().child("errors").child(activityName);
    private Error_class error_class = new Error_class();

    private mydatabase db = new mydatabase(this);

    private ListView lst_Chat;
    private TextView txt_msg;

    private ArrayList<msg_class> onlineMessages = new ArrayList<>();
    private ArrayList<msg_class> offlineMessages = new ArrayList<>();
    private ArrayList<String> profile = new ArrayList<>();
    private String groupNumber, userId, lastmsg;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        try {
            lst_Chat = findViewById(R.id.lst_Chat);
            txt_msg = findViewById(R.id.txt_msg);

            groupNumber = Objects.requireNonNull(getIntent().getExtras()).getString("groupNumber");
            userId = db.getString("SELECT user_id FROM user ORDER BY id DESC LIMIT 1");
            String qry = "SELECT userName, userGender FROM profile ORDER BY id DESC LIMIT 1";
            profile = db.getProfileChat(qry);

            lastmsg = db.getString("SELECT msgId FROM chat WHERE groupNumber ='" + groupNumber + "' ORDER BY id DESC LIMIT1");

            getData();
            getOnlineMsg();

        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object(){
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()){
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
            String qry = "SELECT * FROM chat WHERE groupNumber = '" + groupNumber + "'";
            offlineMessages = db.getMsg(qry);
            if (offlineMessages.get(0).empty.matches("1"))
                viewData();
        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object(){
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()){
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
    private void viewData() {
        try {


        }catch (Exception e){
            String functionName = Objects.requireNonNull(new Object(){
            }.getClass().getEnclosingMethod()).getName();
            int i = 0;
            for (StackTraceElement ste : e.getStackTrace()){
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
    }


    class Listadapter extends BaseAdapter{
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            return null;
        }
    }
}