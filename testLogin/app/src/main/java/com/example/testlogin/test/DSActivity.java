package com.example.testlogin.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testlogin.R;
import com.example.testlogin.databinding.ActivityDsactivityBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class DSActivity extends AppCompatActivity {
    ActivityDsactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDsactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}