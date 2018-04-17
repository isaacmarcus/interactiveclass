package com.example.zwanzigdrei.interactiveclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WeekSelectionActivity extends AppCompatActivity {

    public static Button week1Btn;
    public static Button week2Btn;
    public static Button week3Btn;
    public static Button week4Btn;
    public static Button week5Btn;
    public static Button week6Btn;
    public static Button week7Btn;
    public static Button week8Btn;
    public static Button week9Btn;
    public static Button week10Btn;
    public static Button week11Btn;
    public static Button week12Btn;
    public static Button week13Btn;
    public static Button week14Btn;
    public static Button oneDBtn;
    public static Button twoDBtn;
    public static Button extraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_selection);

        Intent intent = getIntent();

        week1Btn = (Button)findViewById(R.id.button1);
        week2Btn = (Button)findViewById(R.id.button2);
        week3Btn = (Button)findViewById(R.id.button3);
        week4Btn = (Button)findViewById(R.id.button4);
        week5Btn = (Button)findViewById(R.id.button5);
        week6Btn = (Button)findViewById(R.id.button6);
        week7Btn = (Button)findViewById(R.id.button7);
        week8Btn = (Button)findViewById(R.id.button8);
        week9Btn = (Button)findViewById(R.id.button9);
        week10Btn = (Button)findViewById(R.id.button10);
        week11Btn = (Button)findViewById(R.id.button11);
        week12Btn = (Button)findViewById(R.id.button12);
        week13Btn = (Button)findViewById(R.id.button13);
        week14Btn = (Button)findViewById(R.id.button14);
        oneDBtn = (Button)findViewById(R.id.button15);
        twoDBtn = (Button)findViewById(R.id.button16);
        extraBtn = (Button)findViewById(R.id.button17);

        week1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 1");

            }
        });

        week2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 2");

            }
        });

        week3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 3");

            }
        });

        week4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 4");

            }
        });

        week5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 5");

            }
        });

        week6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 6");

            }
        });

        week7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 7");

            }
        });

        week8Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 8");

            }
        });

        week9Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 9");

            }
        });

        week10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 10");

            }
        });

        week11Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 11");

            }
        });

        week12Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 12");

            }
        });

        week13Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 13");

            }
        });

        week14Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCohortSelection("week","Week 14");

            }
        });

        oneDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProjectFolders("1D","1D");

            }
        });

        twoDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProjectFolders("2D","2D");

            }
        });

        extraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToExtraFolder("extra","additionalSlides");

            }
        });

    }


    private void goToCohortSelection(String key, String value){
        Intent myIntent = new Intent(this, CohortSelectionActivity.class);
        myIntent.putExtra(key, value);
        startActivity(myIntent);
    }

    private void goToProjectFolders(String key, String value){
        Intent myIntent = new Intent(this, PDFviewActivity.class);
        myIntent.putExtra(key, value);
        startActivity(myIntent);
    }

    private void goToExtraFolder(String key, String value){
        Intent myIntent = new Intent(this, PDFviewActivity.class);
        myIntent.putExtra(key, value);
        startActivity(myIntent);
    }
}
