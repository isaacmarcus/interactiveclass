package com.example.mengyuan.multiplechoice;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class QuizSubjectActivity extends AppCompatActivity {

    //change this to what you named your txt files
    String scienceString = "filequestion.txt";
    String mathematicsString = "mathematicsquestion.txt";
    String englishString = "englishquestion.txt";


    Button scienceButton, mathematicsButton,englishButton;
    File scienceFile, mathematicsFile, englishFile;
    File sdcard = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scienceButton = findViewById(R.id.scienceButton);
        mathematicsButton = findViewById(R.id.mathematicsButton);
        englishButton = findViewById(R.id.englishButton);



        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scienceFile = new File(sdcard,scienceString);
                if (scienceFile.exists()) {


                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("question", scienceString);
                    startActivity(intent);
                }

                else {

                    Toast.makeText(getApplicationContext(),"Science Quiz Not Available",Toast.LENGTH_SHORT).show();

                }

            }
        });


        mathematicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mathematicsFile = new File(sdcard,mathematicsString);

                if (mathematicsFile.exists()) {

                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("question", "mathematicsquestion.txt");
                    startActivity(intent);

                }


                else {

                    Toast.makeText(getApplicationContext(),"Math Quiz Not Available",Toast.LENGTH_SHORT).show();

                }
            }
        });



        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                englishFile = new File(sdcard,englishString);

                if (englishFile.exists()) {

                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("question", "englishquestion.txt");
                    startActivity(intent);
                }

                else {

                    Toast.makeText(getApplicationContext(),"English Quiz Not Available",Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
