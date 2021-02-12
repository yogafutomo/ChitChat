package com.example.chitchat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

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

        }
    }

    public void translationS(View view) {
    }

    public void back(View view) {
    }

    public void msgSend(View view) {
    }
}