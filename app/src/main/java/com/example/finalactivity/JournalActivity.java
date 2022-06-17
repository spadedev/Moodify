package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class JournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

// Add new journal activity
        MaterialButton addjournalbtn = findViewById(R.id.addnewjournalbtn);
        addjournalbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(JournalActivity.this, AddJournalActivity.class));
            }
        });

        // Saved journals to appear
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Journal> journalList = realm.where(Journal.class).sort("createdTime", Sort.DESCENDING).findAll();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),journalList);
        recyclerView.setAdapter(myAdapter);

        journalList.addChangeListener(new RealmChangeListener<RealmResults<Journal>>() {
            @Override
            public void onChange(RealmResults<Journal> journals) {
                myAdapter.notifyDataSetChanged();
            }
        });



// Bottom Navigation Code
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