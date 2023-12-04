package com.example.testlogin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testlogin.R;
import com.example.testlogin.admin.LoginActivity;
import com.example.testlogin.admin.MainActivity;
import com.example.testlogin.admin.RegisterActivity;
import com.example.testlogin.nodel.HelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserActivity extends AppCompatActivity {
    EditText signupName, signupEmail, signupUsername, signupPassword;

    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(RegisterUserActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}