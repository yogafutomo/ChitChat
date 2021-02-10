package com.example.chitchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
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

        }catch (Exception e){

        }
    }

    public void msgSend(View view) {
    }
}