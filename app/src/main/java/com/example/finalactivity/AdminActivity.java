package com.example.finalactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AdminUser> adminUserArrayList;
    AdminAdapter adminAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data . . . ");
        progressDialog.show();

        recyclerView = findViewById(R.id.admin_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        adminUserArrayList = new ArrayList<AdminUser>();
        adminAdapter = new AdminAdapter(AdminActivity.this , adminUserArrayList);

        recyclerView.setAdapter(adminAdapter);

        logout = findViewById(R.id.button2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LogoActivity.class));
            }
        });

        EventChangeListener();

    }

    private void EventChangeListener() {

        db.collection("users").orderBy("fName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            if(progressDialog.isShowing()) progressDialog.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                adminUserArrayList.add(dc.getDocument().toObject(AdminUser.class));
                            }
                            adminAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing()) progressDialog.dismiss();
                        }

                    }
                });
    }
}