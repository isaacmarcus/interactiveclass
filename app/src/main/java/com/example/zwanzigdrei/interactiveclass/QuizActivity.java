package com.example.zwanzigdrei.interactiveclass;

/**
 * Created by mengyuan on 4/3/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class QuizActivity extends AppCompatActivity {


    String filename;

    QuestionLibrary mQuestionLibrary;



    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    public QuizActivity() throws FileNotFoundException {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        filename = intent.getStringExtra("question");


        try {


            mQuestionLibrary = new QuestionLibrary(filename);


            mScoreView = (TextView) findViewById(R.id.score);
            mQuestionView = (TextView) findViewById(R.id.question);
            mButtonChoice1 = (Button) findViewById(R.id.choice1);
            mButtonChoice2 = (Button) findViewById(R.id.choice2);
            mButtonChoice3 = (Button) findViewById(R.id.choice3);


            updateQuestion();

            //Start of Button Listener for Button1
            mButtonChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for Button goes in here

                    if (mButtonChoice1.getText().toString().equals(mAnswer)) {
                        mScore = mScore + 1;
                        updateScore(mScore);
                        updateQuestion();
                        //This line of code is optiona
                        Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(QuizActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            });

            //End of Button Listener for Button1

            //Start of Button Listener for Button2
            mButtonChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for Button goes in here

                    if (mButtonChoice2.getText().toString().equals(mAnswer)) {
                        mScore = mScore + 1;
                        updateScore(mScore);
                        updateQuestion();
                        //This line of code is optiona
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            });

            //End of Button Listener for Button2


            //Start of Button Listener for Button3
            mButtonChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //My logic for Button goes in here

                    if (mButtonChoice3.getText().toString().equals(mAnswer)) {
                        mScore = mScore + 1;
                        updateScore(mScore);
                        updateQuestion();
                        //This line of code is optiona
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            });

            //End of Button Listener for Button3
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }

    private void updateQuestion(){

        if (mQuestionNumber < 4) {

            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }

        else {

            Intent intent = new Intent(getApplicationContext(),ScorePage.class);
            intent.putExtra("score",mScore);
            startActivity(intent);

        }
    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }
}
