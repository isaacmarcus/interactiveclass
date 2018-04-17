package com.example.zwanzigdrei.interactiveclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CohortSelectionActivity extends AppCompatActivity {

    private static Button class1Btn;
    private static Button class2Btn;
    private static Button viewStuBtn;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cohort_selection);


        Intent intent = getIntent();
        final String week = intent.getStringExtra("week");

        this.bundle = LoginActivity.bundle;
        bundle.putString("week",week);
        String rank = bundle.getString("rank");

        class1Btn = (Button)findViewById(R.id.button18);
        class2Btn = (Button)findViewById(R.id.button19);
        viewStuBtn = (Button)findViewById(R.id.stuBtn);

        if (!rank.equals("teacher")) {
            viewStuBtn.setVisibility(View.GONE);
        }


        class1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySlidesActivity("filename",week+" Cohort 1");

            }
        });

        class2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySlidesActivity("filename",week+" Cohort 2");

            }
        });

        viewStuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewStuActivity("week",week);
            }
        });
    }

    private void displaySlidesActivity(String key, String value){
        Intent myIntent = new Intent(this, PDFviewActivity.class);
        myIntent.putExtra(key, value);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }

    private void viewStuActivity(String key, String value){
        Intent myIntent = new Intent(this, ViewStudentsActivity.class);
        myIntent.putExtra(key, value);
        startActivity(myIntent);
    }

}
