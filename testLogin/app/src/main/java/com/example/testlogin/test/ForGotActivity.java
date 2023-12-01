package com.example.testlogin.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testlogin.R;
import com.example.testlogin.databinding.ActivityForGotBinding;
import com.example.testlogin.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForGotActivity extends AppCompatActivity {
 ActivityForGotBinding binding;
 ProgressDialog dialog;
 FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForGotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(ForGotActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        
        binding.btnForgotIn.setOnClickListener(view -> {
            forgirPass();
        });

    }

    private void forgirPass() {

        dialog.show();
        auth.sendPasswordResetEmail(binding.edEmailIn.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(ForGotActivity.this, LoginActivity.class));
                    finish();
                    Toast.makeText(ForGotActivity.this, "Check email", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForGotActivity.this, "nhập lại email", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForGotActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}