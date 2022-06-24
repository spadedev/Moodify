package com.example.finalactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    EditText _input_email, _input_password;
    Button _button_signin, _button_signup, _button_forgotpass;
    RadioButton _radioButton_admin, _radioButton_user;
    ProgressBar progressBar2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        progressBar2 = findViewById(R.id.progressBar2);
        _input_email = findViewById(R.id.input_email);
        _input_password = findViewById(R.id.input_password);
        _button_signin = findViewById(R.id.button_signin);
        _button_signup = findViewById(R.id.button_signup);
        _radioButton_admin = findViewById(R.id.radioButton_admin);
        _radioButton_user = findViewById(R.id.radioButton_user);
        _button_forgotpass = findViewById(R.id.button_forgot);
        fAuth = FirebaseAuth.getInstance();

        _button_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        _button_signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(_radioButton_admin.isChecked()){
                    Intent intent = new Intent(StartActivity.this, AdminActivity.class);
                    startActivity(intent);
                }

                else if (_radioButton_user.isChecked()) {

                    String email = _input_email.getText().toString().trim();
                    String password = _input_password.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        _input_email.setError("Email is Required");
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        _input_password.setError("Password is Required");
                        return;
                    }

                    if(password.length()<6){
                        _input_password.setError("Password must be >= 6 characters.");
                        return;
                    }

                    progressBar2.setVisibility(View.VISIBLE);

                    // authenticate the user

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(StartActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), QuotesActivity.class));
                            }
                            else{
                                Toast.makeText(StartActivity.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                progressBar2.setVisibility(View.GONE);
                            }
                        }
                    });

                    Intent intent = new Intent(StartActivity.this, QuotesActivity.class);
                    startActivity(intent);
                }
            }

        });


        _button_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter your email to receive reset link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(StartActivity.this, "Reset link sent to your email.", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StartActivity.this, "Error! Reset link is not sent." + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
}
