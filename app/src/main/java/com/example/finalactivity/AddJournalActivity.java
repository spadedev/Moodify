package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddJournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savejournal);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        // Adding journal entry
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String title = titleInput.getText().toString();
                String description =descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Journal journal = realm.createObject(Journal.class);
                journal.setTitle(title);
                journal.setDescription(description);
                journal.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Journal saved", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        // Bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homepage);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homepage:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.moodchart:
                        startActivity(new Intent(getApplicationContext()
                                , MoodChartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.options:
                        startActivity(new Intent(getApplicationContext()
                                , OptionActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}