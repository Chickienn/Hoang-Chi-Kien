package com.example.testmodel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testmodel.database.UserDB;
import com.example.testmodel.model.UserModel;

public class SignInActivity extends AppCompatActivity {
    ImageView ivIcon;
    EditText edtEmail, edtPass;
    Button btnSignIn;
    ImageButton btnGG, btnFB, btnX;
    TextView tvSignUp;
    UserDB userDB;
    UserModel userModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ivIcon = findViewById(R.id.ivIcon);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnGG = findViewById(R.id.btnGG);
        btnFB = findViewById(R.id.btnFB);
        btnX = findViewById(R.id.btnX);
        tvSignUp = findViewById(R.id.tvSignUp);
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent1);
                finish();
            }
        });
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
        userDB = new UserDB(SignInActivity.this);
        checkLoginWithDatabase();
    }

    private void checkLoginWithDatabase(){
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    edtEmail.setError("Email can be not empty");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    edtPass.setError("Password can be not empty");
                    return;
                }
                userModel = userDB.getInfoUser(email, pass); // lay du lieu tu database
                assert userModel != null;
                if (userModel.getUsername() != null){
                    // dang nhap thanh cong
                    Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_ACCOUNT", email);
                    bundle.putInt("ID_ACCOUNT", userModel.getId());
                    intent.putExtras(bundle);
                    startActivity(intent); // chuyen sang HomeActivity
                    finish();
                } else {
                    // dang nhap that bai
                    Toast.makeText(SignInActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

