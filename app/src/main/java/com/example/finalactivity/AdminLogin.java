package com.example.finalactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText password;
    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        password = findViewById(R.id.password);
        admin = findViewById(R.id.loginBtn);


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = password.getText().toString().trim();

                if (pass.equals("nanaymoto")){
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                }
                else{
                    Toast.makeText(AdminLogin.this, "Error! Wrong password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}