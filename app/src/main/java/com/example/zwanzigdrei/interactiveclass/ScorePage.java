package com.example.mengyuan.multiplechoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        Intent intent = getIntent();
        Integer score  = intent.getIntExtra("score",0);

        textView = findViewById(R.id.textView);


        textView.setText("You scored "+score.toString()+" for the quiz!");


        Button btn = (Button)findViewById(R.id.open_activity_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScorePage.this, QuizSubjectActivity.class));
            }
        });


    }
}
