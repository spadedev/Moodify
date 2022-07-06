package com.example.finalactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmotionsActivity extends AppCompatActivity {

    RelativeLayout happy;
    RelativeLayout excited;
    RelativeLayout sad;
    RelativeLayout bored;
    RelativeLayout angry;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String UserID;
    CharSequence desc;

    Button save;
    ImageView mood;
    TextView description;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);

        happy = findViewById(R.id.happy);
        excited = findViewById(R.id.excited);
        sad = findViewById(R.id.sad);
        angry = findViewById(R.id.angry);
        bored = findViewById(R.id.bored);


        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        save = findViewById(R.id.saveBtn);
        mood = findViewById(R.id.moodDisplay);
        description = findViewById(R.id.description);

        description.setEnabled(true);
        description.setFocusableInTouchMode(true);
        description.setFocusable(true);
        description.setMovementMethod(null);


        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood.setImageResource(R.drawable.happy);
            }
        });

        excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood.setImageResource(R.drawable.superhappy);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood.setImageResource(R.drawable.sad);
            }
        });

        bored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood.setImageResource(R.drawable.neutral);
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood.setImageResource(R.drawable.angry);
            }
        });


        Date currentTime = Calendar.getInstance().getTime();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc = description.getText();
                UserID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("users").child(UserID);
                Map<String, String> map = new HashMap<>();
                map.put("time",currentTime.toString());
                map.put("description",desc.toString());
                //map.put(currentTime.toString(),desc);
                reference.push().setValue(map);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}