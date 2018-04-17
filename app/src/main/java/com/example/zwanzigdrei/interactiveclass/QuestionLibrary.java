package com.example.zwanzigdrei.interactiveclass;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by mengyuan on 4/3/2018.
 */

public class QuestionLibrary {
    private String[] mQuestions = new String[4];
    private String[][] mChoices = new String [4][3];
    private String[] mCorrectAnswers = new String[4];

    public QuestionLibrary(String filename) throws FileNotFoundException {


        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File (sdcard, filename);
            Scanner scanner = new Scanner(file);


            String line ="";
            int number = 0 ;
            int array = 0;
            while (scanner.hasNextLine()) {
                line= scanner.nextLine();
                if (!line.isEmpty()) {
                    if (array == 0 ) {
                        mQuestions[number] = line;
                        number++;
                    }
                    else if (array == 1 ) {

                        String[] templine = line.split(",");
                        mChoices[number][0] = templine[0];
                        mChoices[number][1] = templine[1];
                        mChoices[number][2] = templine[2];



                        number++;
                    }
                    else if (array == 2 ) {

                        String templine[] = line.split(",");
                        mCorrectAnswers[0] = templine[0];
                        mCorrectAnswers[1] = templine[1];
                        mCorrectAnswers[2] = templine[2];
                        mCorrectAnswers[3] = templine[3];
                    }

                } else{
                    number =0;
                    array++;
                }

            }
//            fileInputStream.close();
//            line = stringBuilder.toString();
//            bufferedReader.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("questions",Arrays.toString(mQuestions));
        Log.i("choices",Arrays.deepToString(mChoices));
        Log.i("answers",Arrays.toString(mCorrectAnswers));
        System.out.println(Arrays.deepToString(mChoices));
        Log.i("answer0",mCorrectAnswers[0]);
        Log.i("answer0",mCorrectAnswers[1]);
        Log.i("answer0",mCorrectAnswers[2]);
        Log.i("answer0",mCorrectAnswers[3]);


    }
//
//    private String mQuestions[] = {
//            "Which part of the plant holds it in the soil?",
//            "This part of the plant absorbs energy from the sun.",
//            "This part of the plant attracts bees, butterflies and hummingbirds.",
//            "The _______ holds the plant upright."
//
//            };
//
//
//    private String mChoices[][] = {
//            {"Roots", "Stem", "Flower"},
//            {"Fruit", "Leaves", "Seeds"},
//            {"Bark", "Flower", "Roots"},
//            {"Flower", "Leaves", "Stem"}
//            };
//
//
//    private String mCorrectAnswers[] = {"Roots", "Leaves", "Flower", "Stem"};


    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
