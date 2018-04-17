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

    public static Bundle bundle = new Bundle();

    private EditText Username;
    private EditText Password;
    private Button Login;

    private static DatabaseReference fbstudentID;
    private static DatabaseReference fbPassword;
    private static DatabaseReference fbRank;

    private static String dataRank;
    private static String dataStudentID;

    public static final String MY_PREF = "MyPref";
    public static final String KEY = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bundle = new Bundle();

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

        fbRank.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataRank = dataSnapshot.getValue().toString();
                bundle.putString("rank",dataRank); // store rank into bundle to move across intent
                bundle.putString("studentID",Username.getText().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        fbstudentID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataStudentID = dataSnapshot.getValue().toString();
                bundle.putString("studentID",Username.getText().toString()); // store id into bundle to move across intent
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

                    // Move to next screen (MainActivity)
                    Intent mainInt = new Intent(LoginActivity.this, MainActivity.class);
                    mainInt.putExtras(bundle);
                    if (dataRank.equals("teacher")) {
                        Toast.makeText(LoginActivity.this, "TEACHER LOGIN", Toast.LENGTH_SHORT).show();
                        startActivity(mainInt);
                    } else if (dataRank.equals("student")) {
                        Toast.makeText(LoginActivity.this, "STUDENT LOGIN", Toast.LENGTH_SHORT).show();
                        startActivity(mainInt);
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
