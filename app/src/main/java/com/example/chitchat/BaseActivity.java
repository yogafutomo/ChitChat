package com.example.chitchat;

import android.app.ProgressDialog;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @VisibleForTesting
    public ProgressDialog mProgressDialog;
}
