package com.example.testlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testlogin.test.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private LinearLayout layoutSignUp;
    private Button btnSignIn;
    private EditText edMail,edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initui();
        initListener();
    }

    private void initui(){
        btnSignIn = findViewById(R.id.btn_sign_in);
        layoutSignUp = findViewById(R.id.sing_up);
        edMail = findViewById(R.id.ed_email_in);
        edPass = findViewById(R.id.ed_pass_in);
    }

    private void initListener() {
        layoutSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edMail.getText().toString();
                String password = edPass.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    onClickSignIn(email, password);
                } else {
                    // Xử lý khi email hoặc password không hợp lệ
                    // Ví dụ: Hiển thị thông báo lỗi cho người dùng
                }

            }
        });
    }

    // Hàm đăng nhập bằng email và mật khẩu
    private void onClickSignIn(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                            // Đăng nhập thành công, bạn có thể thực hiện các hành động sau khi đăng nhập ở đây
                        } else {
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // Đăng nhập thất bại, xử lý lỗi ở đây
                        }
                    }
                });
    }
}