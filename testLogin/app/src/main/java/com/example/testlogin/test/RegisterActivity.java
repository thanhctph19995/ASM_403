package com.example.testlogin.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.example.testlogin.MainActivity;
import com.example.testlogin.R;
import com.example.testlogin.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private  String emailStr, passStr, nameStr, phoneStr;
    ProgressDialog dialog;
    private String deviceId;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        deviceId = Settings.Secure.getString(RegisterActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);

        auth = FirebaseAuth.getInstance();

        dialog= new ProgressDialog(RegisterActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("loading...");
        
        binding.btnSignUp.setOnClickListener(view -> {
            registerUser();

        });
    }

    private void registerUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");
        final FirebaseUser user = auth.getCurrentUser();
        nameStr = binding.edFullName.getEditableText().toString();
        emailStr = binding.edEmail.getText().toString();
        passStr = binding.edPass.getText().toString();
        phoneStr = binding.edPhone.getText().toString();

        Query query= reference.orderByChild("diviceId").equalTo(deviceId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(RegisterActivity.this, "thiết bị đã đc đang ký", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUpUser(user, nameStr, emailStr, passStr);
                    dialog.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void signUpUser(FirebaseUser user, String nameStr, String emailStr, String passStr) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");
        auth.createUserWithEmailAndPassword(emailStr, passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    HashMap<String, Object> map = new HashMap<>();

                    map.put("Email", emailStr);
                    map.put("name", nameStr);
                    map.put("password", passStr);
                    map.put("phone", phoneStr);
                    map.put("userId", userId);

                    reference.child(userId).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                        }
                    });

                }
            }

        });
    }
}