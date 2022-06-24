package com.example.finalactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminSeekHelp extends AppCompatActivity {

    private EditText title, content;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_seek_help);
        title = findViewById(R.id.admintitlesh);
        content = findViewById(R.id.admincontentsh);
        add = findViewById(R.id.save);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String admintitle = title.getText().toString();
                String admincontent = content.getText().toString();

                Intent intent = new Intent(AdminSeekHelp.this, SeekHelpActivity.class);
                intent.putExtra("keytitle", admintitle);
                intent.putExtra("keycontent", admincontent);
                startActivity(intent);
            }
        });
    }
}