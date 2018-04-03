package com.example.zwanzigdrei.interactiveclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;

    private static DatabaseReference fbstudentID;
    private static DatabaseReference fbPassword;
    private static DatabaseReference fbRank;

    private static String dataRank;

    public static final String MY_PREF = "MyPref";
    public static final String KEY = "Username";

    //private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(),Password.getText().toString());
            }
        });

    }

    public void validate(final String username, final String password) {
        fbstudentID = FirebaseDatabase.getInstance().getReference().child("Users").child(username.toString()).child("StudentID");
        fbPassword = FirebaseDatabase.getInstance().getReference().child("Users").child(username.toString()).child("Password");
        fbRank = FirebaseDatabase.getInstance().getReference().child("Users").child(username.toString()).child("Rank");

        //final String[] dataRank = new String[1];
        fbRank.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataRank = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        fbPassword.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dataPassword = dataSnapshot.getValue().toString();
                if (password.equals(dataPassword)) {
                    SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY, username);
                    editor.apply();

                    if (dataRank.equals("teacher")) {
                        Toast.makeText(LoginActivity.this, "TEACHER LOGIN", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, TeacherActivity.class));
                    } else if (dataRank.equals("student")) {
                        Toast.makeText(LoginActivity.this, "STUDENT LOGIN", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong User ID/Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    static boolean returnVal=false;

    public static boolean validateTest(final String username, final String password) {
        fbstudentID = FirebaseDatabase.getInstance().getReference().child("Users").child(username.toString()).child("StudentID");
        fbPassword = FirebaseDatabase.getInstance().getReference().child("Users").child(username.toString()).child("Password");
        fbRank = FirebaseDatabase.getInstance().getReference().child("Users").child(username.toString()).child("Rank");

        //final String[] dataRank = new String[1];
        fbRank.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataRank = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        fbPassword.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dataPassword = dataSnapshot.getValue().toString();
                if (password.equals(dataPassword)) {
                    if (dataRank.equals("teacher")) {
                        returnVal = true;
                    } else if (dataRank.equals("student")) {
                        returnVal = true;
                    }
                } else {
                    returnVal = false;

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return returnVal;
    }
}
