package com.example.finalactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class StartActivity extends AppCompatActivity {

    EditText _input_email, _input_password;
    Button _button_signin, _button_signup;
    RadioButton _radioButton_admin, _radioButton_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        _input_email = (EditText)findViewById(R.id.input_email);
        _input_password = (EditText)findViewById(R.id.input_password);
        _button_signin = (Button)findViewById(R.id.button_signin);
        _button_signup = (Button)findViewById(R.id.button_signup);
        _radioButton_admin = (RadioButton)findViewById(R.id.radioButton_admin);
        _radioButton_user = (RadioButton)findViewById(R.id.radioButton_user);

        _button_signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(_radioButton_admin.isChecked()){
                    Intent intent = new Intent(StartActivity.this, AdminActivity.class);
                    startActivity(intent);
                } else if (_radioButton_user.isChecked()) {
                    Intent intent = new Intent(StartActivity.this, QuotesActivity.class);
                    startActivity(intent);
                }
            }
        });

        _button_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}