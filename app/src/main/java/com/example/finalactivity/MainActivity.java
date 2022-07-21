package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgbtn_journal;
    private ImageButton imgbtn_exercise;
    private ImageButton imgbtn_covid;
    private ImageButton imgbtn_chart;
    private ImageButton imgbtn_seekhelp;
    private ImageButton imgbtn_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homepage);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.moodchart:
                        startActivity(new Intent(getApplicationContext()
                                , MoodChartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.homepage:
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

        imgbtn_journal = (ImageButton) findViewById(R.id.imgbtn_journal);
        imgbtn_journal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openJournalActivity();
            }
        });

        imgbtn_exercise = (ImageButton) findViewById(R.id.imgbtn_exercise);
        imgbtn_exercise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openExerciseActivity();
            }
        });

        imgbtn_covid = (ImageButton) findViewById(R.id.imgbtn_covid);
        imgbtn_covid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCovidActivity();
            }
        });

        imgbtn_chart = (ImageButton) findViewById(R.id.imgbtn_chart);
        imgbtn_chart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMoodChartActivity();
            }
        });

        imgbtn_seekhelp = (ImageButton) findViewById(R.id.imgbtn_seekhelp);
        imgbtn_seekhelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSeekHelpActivity();
            }
        });

        imgbtn_options = (ImageButton) findViewById(R.id.imgbtn_options);
        imgbtn_options.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openOptionActivity();
            }
        });

    }
    public void openJournalActivity(){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    public void openExerciseActivity(){
        Intent intent = new Intent(this, ExerciseJournal.class);
        startActivity(intent);
    }

    public void openCovidActivity(){
        Intent intent = new Intent(this, CovidActivity.class);
        startActivity(intent);
    }

    public void openMoodChartActivity(){
        Intent intent = new Intent(this, MoodChartActivity.class);
        startActivity(intent);
    }

    public void openSeekHelpActivity(){
        Intent intent = new Intent(this, SeekHelpActivity.class);
        startActivity(intent);
    }

    public void openOptionActivity(){
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
    }
}