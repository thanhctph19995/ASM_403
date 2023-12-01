package com.example.testlogin.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.testlogin.R;
import com.example.testlogin.databinding.ActivityPhoneBinding;

public class PhoneActivity extends AppCompatActivity {

    ActivityPhoneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
    }
}