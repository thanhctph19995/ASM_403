package com.example.testlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText edFullName, edEmail, edPass, edRePass, edPhone, edDate;
    private Button btnSignUp;
    private ImageView imgAvata;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        iniui();
        initListener();
    }

    private void iniui(){
        imgAvata = findViewById(R.id.img_avata);
        edFullName = findViewById(R.id.ed_full_name);
        edEmail = findViewById(R.id.ed_email);
        edPass = findViewById(R.id.ed_pass);
        edRePass = findViewById(R.id.ed_repass);
        edPhone = findViewById(R.id.ed_phone);
        edDate = findViewById(R.id.ed_date);
        progressDialog = new ProgressDialog(this);

        btnSignUp = findViewById(R.id.btn_sign_up);

    }

    private void showUserInFoemation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if (name == null){
            edFullName.setVisibility(View.GONE);
        }else {
            edFullName.setVisibility(View.VISIBLE);
            edFullName.setText(name);
        }

        edEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.avatar).into(imgAvata);
    }

    private void initListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });
    }

    public void onClickSignUp() {
        String fullName = edFullName.getText().toString().toString();
        String email = edEmail.getText().toString().toString();
        String password = edPass.getText().toString().toString();
        String strRePass = edRePass.getText().toString().toString();
        String phone = edPhone.getText().toString().toString();
        String date = edDate.getText().toString().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}