package com.example.testmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testmodel.database.UserDB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity {
    ImageButton btnBack, btnMain, btnGG, btnFB, btnX;
    EditText edtName, edtEmail, edtPass, edtPassconfirm;
    CheckBox checkBox;
    Button btnSignUp;
    TextView tvSignIn;
    UserDB userDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassconfirm = findViewById(R.id.edtPassconfirm);
        edtPass = findViewById(R.id.edtPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnGG = findViewById(R.id.btnGG);
        btnFB = findViewById(R.id.btnFB);
        btnX = findViewById(R.id.btnX);
        tvSignIn = findViewById(R.id.tvSignIn);
        checkBox = findViewById(R.id.cb);
        btnBack = findViewById(R.id.btnBack);
        btnMain = findViewById(R.id.btnMain);
        userDB = new UserDB(SignUpActivity.this);

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=IzSYlr3VI1A"; // Replace with your URL
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=nH_GMngQfzw"; // Replace with your URL
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        btnGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"; // Replace with your URL
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //File file = null; // save data to file in local storage
                String name = edtName.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String passConfirm = edtPassconfirm.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    edtName.setError("Username can not empty");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    edtPass.setError("Password can not empty");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    edtEmail.setError("Email can not empty");
                    return;
                }if (TextUtils.isEmpty(passConfirm)){
                    edtPassconfirm.setError("Password need to be confirmed");
                    return;
                }
                if (!passConfirm.equals(pass)){
                    edtPassconfirm.setError("Password does not match!");
                    return;
                }
                if (!checkBox.isChecked()){
                    checkBox.setError("You need to read terms & policies");
                }

                boolean checkName = userDB.checkUsernameEmail(email, 1);
                boolean checkEmail = userDB.checkUsernameEmail(pass, 2);
                if (checkName){
                    Toast.makeText(SignUpActivity.this, "Username Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkEmail){
                    Toast.makeText(SignUpActivity.this, "Email Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                long insert = userDB.addNewUser(name, pass, email);
                if (insert == -1){
                    Toast.makeText(SignUpActivity.this, "signup Fail", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "signup successfully", Toast.LENGTH_SHORT).show();
                    // quay ve man hinh dang nhap
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
