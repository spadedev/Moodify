package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class JournalActivity extends AppCompatActivity {

    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = fStore.collection("journal");

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

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

        // Add new journal activity
        MaterialButton addjournalbtn = findViewById(R.id.addnewjournalbtn);
        addjournalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JournalActivity.this, AddJournalActivity.class));
            }
        });

        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        Query query = notebookRef.orderBy("createdTime", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Journal> options = new FirestoreRecyclerOptions.Builder<Journal>().setQuery(query, Journal.class).build();

        adapter = new MyAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener(){
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {

            }

            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position){
                Journal journal = documentSnapshot.toObject(Journal.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Toast.makeText(JournalActivity.this, "Position: " + position + "ID" + id, Toast.LENGTH_SHORT).show();
            }
        });
        }

    protected void OnStart(){
        super.onStart();
        adapter.startListening();
        }

    protected void OnStop(){
        super.onStop();
        adapter.stopListening();
        }
    }
