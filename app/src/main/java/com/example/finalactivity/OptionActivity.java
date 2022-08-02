package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;

import javax.annotation.Nullable;

public class OptionActivity<StorageReference> extends AppCompatActivity {

    TextView fullName,email;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        //Bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewOption);
        bottomNavigationView.setSelectedItemId(R.id.options);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.moodchart:
                        startActivity(new Intent(getApplicationContext()
                                , MoodChartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.homepage:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.options:
                        return true;
                }
                return false;
            }
        });


        fullName = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        UserId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();


        // Getting data in firebase
        DocumentReference documentReference = fStore.collection("users").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e == null){
                    if (documentSnapshot.exists()) {
                        fullName.setText(documentSnapshot.getString("fName"));
                        email.setText(documentSnapshot.getString("email"));

                    } else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            }
        });

    }

        public void logout(View view){
            fAuth.signOut();//logout
            startActivity(new Intent(getApplicationContext(), LogoActivity.class));
            finish();


    }
}