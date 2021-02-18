package com.example.chitchat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

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
public class OneToOneChats extends AppCompatActivity {

    private static final String activityName = "OneToOneChats";
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference();
    private static final DatabaseReference myErrorRef = database.getReference().child("errors").child(activityName);
    private Error_class error_class = new Error_class();

    private mydatabase db = new mydatabase(this);

    private ListView Lst_conversation;

    private ArrayList<String> a_partnersName = new ArrayList<>();
    private ArrayList<String> a_partnersId = new ArrayList<>();
    private ArrayList<msg_class> a_lastMsg = new ArrayList<>();
    private ArrayList<msg_class> a_onlineMsgs = new ArrayList<>();
    private ArrayList<String> a_roomsKey = new ArrayList<>();

    private String userId;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_one_chats);
        try {
            Lst_conversation = findViewById(R.id.Lst_conversation);

            userId = db.getString("SELECT userId FROM user ORDER BY id DESC LIMIT 1");

            getRoomsKey();
            _viewData();
            Lst_conversation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    goToOneToOne(position);
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


    private void getRoomsKey(){
        try {
            myRef.child("oneToOneRooms").child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren())
                        a_roomsKey.add(singleSnapshot.getKey());

                    if (a_roomsKey.size() > 0)
                        _getsRoomKeys();
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

    private void _getsRoomKeys(){
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

    private void getMsgs(){
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

    private void _getMsgs(){
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

    private void saveLocalDB(){
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

    private void _viewData(){
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

    private void viewData(){
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

    private void goToOneToOne(int position) {
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

            return a_partnersName.size();
        }

        @Override
        public Object getItem(int position) {

            return a_partnersName.get(position);
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