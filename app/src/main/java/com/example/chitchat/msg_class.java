package com.example.chitchat;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class msg_class {
    public String msgId, userId, msg, UserGender, UserName;

    public msg_class(String msgId, String userId, String msg, String userGender, String userName) {
        this.msgId = msgId;
        this.userId = userId;
        this.msg = msg;
        this.UserGender = userGender;
        this.UserName = userName;
    }

    @Exclude
    Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("msgId", msgId);
        result.put("userId", userId);
        result.put("msg", msg);
        result.put("UserGender", UserGender);
        result.put("UserName", UserName);
        return result;
    }
}
