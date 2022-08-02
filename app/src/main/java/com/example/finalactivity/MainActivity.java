package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgbtn_journal;
    private ImageView imgbtn_exercise;
    private ImageView imgbtn_covid;
    private ImageView imgbtn_chart;
    private ImageView imgbtn_seekhelp;
    private ImageView imgbtn_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewMain);
        bottomNavigationView.setSelectedItemId(R.id.homepage);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
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

        imgbtn_journal = (ImageView) findViewById(R.id.imgbtn_journal);
        imgbtn_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJournalActivity();
            }
        });

        imgbtn_exercise = (ImageView) findViewById(R.id.imgbtn_exercise);
        imgbtn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExerciseActivity();
            }
        });

        imgbtn_covid= (ImageView) findViewById(R.id.imgbtn_covid);
        imgbtn_covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCovidActivity();
            }
        });

        imgbtn_chart = (ImageView) findViewById(R.id.imgbtn_chart);
        imgbtn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoodChartActivity();
            }
        });

        imgbtn_seekhelp = (ImageView) findViewById(R.id.imgbtn_seekhelp);
        imgbtn_seekhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeekHelpActivity();
            }
        });

        imgbtn_options = (ImageView) findViewById(R.id.imgbtn_options);
        imgbtn_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionActivity();
            }
        });
    }
    public void openJournalActivity() {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
    public void openExerciseActivity() {
        Intent intent = new Intent(this, ExerciseJournal.class);
        startActivity(intent);
    }
    public void openCovidActivity() {
        Intent intent = new Intent(this, CovidActivity.class);
        startActivity(intent);
    }
    public void openMoodChartActivity() {
        Intent intent = new Intent(this, MoodChartActivity.class);
        startActivity(intent);
    }
    public void openSeekHelpActivity() {
        Intent intent = new Intent(this, SeekHelpActivity.class);
        startActivity(intent);
    }
    public void openOptionActivity() {
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
    }

}