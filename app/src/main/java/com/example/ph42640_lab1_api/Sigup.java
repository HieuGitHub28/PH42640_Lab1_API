package com.example.ph42640_lab1_api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sigup extends AppCompatActivity {

    Button btnSignup;
    ImageView imgBack;
    TextInputEditText txtEmailDK;
    TextInputEditText txtPassDk;

    String email,password;

    private FirebaseAuth mAuth; // b1 : khởi tạo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);
        txtEmailDK = findViewById(R.id.txtEmailDK);
        txtPassDk = findViewById(R.id.txtPassDK);
        btnSignup = findViewById(R.id.btnSignup);
        imgBack = findViewById(R.id.imgBack);

        mAuth = FirebaseAuth.getInstance(); // b2 : gán giá trị

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = String.valueOf(txtEmailDK.getText());
                password = String.valueOf(txtPassDk.getText());
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Sigup.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                }else{
                    // b4 : sử dụng hàm
                    checkSignup(email,password);
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sigup.this, Login_PH42640.class));
            }
        });

    }

    // b3 : viết hàm đăng ký
    private void checkSignup(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();

                    String emailUser = user.getEmail();

                    SharedPreferences preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email",emailUser);
                    editor.apply();

                    Toast.makeText(Sigup.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sigup.this, Login_PH42640.class));
                }else {
                    Log.w("Lỗi","createUserWithEmail: failure",task.getException());
                    Toast.makeText(Sigup.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}