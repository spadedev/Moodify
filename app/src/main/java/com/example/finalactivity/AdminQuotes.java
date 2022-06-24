package com.example.finalactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminQuotes extends AppCompatActivity {

    private EditText content;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_quotes);

        content = findViewById(R.id.admincontentsh);
        add = findViewById(R.id.save);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admincontent = content.getText().toString();

                Intent intent = new Intent(AdminQuotes.this, QuotesActivity.class);
                intent.putExtra("keycontent", admincontent);
                startActivity(intent);
            }
        });
    }
}