package com.example.finalactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogoActivity extends AppCompatActivity {

    Button login, register, admin, buttonadmin;
    EditText adminpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        admin = findViewById(R.id.register);
        buttonadmin = findViewById(R.id.gotoadmin);
        adminpassword = findViewById(R.id.adminpassword);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonadmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String password = adminpassword.getText().toString().trim();

                        if(TextUtils.isEmpty(password)){
                            adminpassword.setError("Password is Required.");
                            return;
                        }

                        if(password == "mamamo"){
                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StartActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}