package com.example.testlogin.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testlogin.MainActivity;
import com.example.testlogin.R;
import com.example.testlogin.SignInActivity;
import com.example.testlogin.databinding.ActivityLoginBinding;
import com.example.testlogin.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;
    String emailStr, passStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        binding.btnSignIn.setOnClickListener(view -> {
            logInUser();
        });
        binding.singUp.setOnClickListener(view ->{
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finishAffinity();
        });
        binding.btnForgotIn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForGotActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }

    private void logInUser() {
        emailStr = binding.edEmailIn.getText().toString();
        passStr = binding.edPassIn.getText().toString();
        dialog.show();

        logInUsers(emailStr, passStr);
    }

    private void logInUsers(String emailStr, String passStr) {
        auth.signInWithEmailAndPassword(emailStr, passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}