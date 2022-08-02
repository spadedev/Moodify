package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmotionsActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    ImageView sad, happy, excited, angry, bored;

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
                mood.setImageResource(R.drawable.superhappy);
            }
        });

        excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood.setImageResource(R.drawable.happy);
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

                if(TextUtils.isEmpty(desc)){
                    description.setError("Description is required!");
                    return;
                }

                //FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                //DatabaseReference reference = rootNode.getReference("users").child(UserID);
                //Map<String, String> map = new HashMap<>();
                //map.put("time",currentTime.toString());
                //map.put("description",desc.toString());
                // map.put(currentTime.toString(),desc);
                //reference.push().setValue(map);

                UserID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("users").document(UserID).collection("emotions").document(String.valueOf(currentTime));
                Map<String,Object> user = new HashMap<>();
                user.put("time",currentTime.toString());
                user.put("description",desc.toString());

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: user Profile is created for "+ UserID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}