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
//    private String[] mQuestions = new String[4];
//    private String[][] mChoices = new String [4][3];
//    private String[] mCorrectAnswers = new String[4];

///    final static String fileName = "filequestion.txt";
  //  final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" ;

//    public QuestionLibrary() throws FileNotFoundException {
//
////        var path = global::Android.OS.Environment.ExternalStorageDirectory.AbsolutePath;
////        var filename = Path.Combine(path.ToString(), "myfile.txt");
//
//
//        //  File file = new File("C:\\Users\\mengyuan\\Downloads\\Multiplechoice\\app\\src\\main\\java\\com\\example\\mengyuan\\multiplechoice\\filequestion.txt");
////        try {
//////            String text="";
//////            FileInputStream Fin=new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "filequestion.txt"));
//////            byte[] b=new byte[100];
//////            Fin.read(b);
//////            text=new String(b);
//////            Fin.close();
////
////            File sdcard = Environment.getExternalStorageDirectory();
////            File file = new File (sdcard, "filequestion.txt");
////            BufferedReader br = new BufferedReader(new FileReader(file));
////
////
////
////       //     FileInputStream fileInputStream = new FileInputStream(new File(path + fileName));
////         //   InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//////            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//////            StringBuilder stringBuilder = new StringBuilder();
////
////
////            //final Resources resources = mHelperContext.getResources();
////            //InputStream inputStream = resources.openRawResource(R.raw.definitions);
////            //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
////
////            String line = "";
////            int number = 0;
////            int array = 0;
////
////            StringBuilder stringBuilder = new StringBuilder();
////            while ((line = br.readLine()) != null) {
////
////                stringBuilder.append(line);
////                stringBuilder.append("\n");
////
////                //line = br.readLine();
////                /*if (!("".equals(line))) {
////                    if (array == 0) {
////                        mQuestions[number] = line;
////                        number++;
////                    } else if (array == 1) {
////                        String arrayc[] = {line};
////                        mChoices[number] = arrayc;
////                        number++;
////                    } else if (array == 2) {
////                        String arrayright[] = {line};
////                        mCorrectAnswers = arrayright;
////                        number++;
////                    }
////
////                } else {
////                    number = 0;
////                    array++;
////                }*/
////
////            }
//////            fileInputStream.close();
//////            line = stringBuilder.toString();
//////            bufferedReader.close();
////            br.close();
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
//
//        try {
//
//            File sdcard = Environment.getExternalStorageDirectory();
//            File file = new File (sdcard, "filequestion.txt");
//            Scanner scanner = new Scanner(file);
//
//
//            String line ="";
//            int number = 0 ;
//            int array = 0;
//            while (scanner.hasNextLine()) {
//                line= scanner.nextLine();
//                if (!line.isEmpty()) {
//                    if (array == 0 ) {
//                        mQuestions[number] = line;
//                        number++;
//                    }
//                    else if (array == 1 ) {
//
//                            String[] templine = line.split(",");
//                            mChoices[number][0] = templine[0];
//                            mChoices[number][1] = templine[1];
//                            mChoices[number][2] = templine[2];
//
//
//
//                        number++;
//                    }
//                    else if (array == 2 ) {
//
//                        String templine[] = line.split(",");
//                        mCorrectAnswers[0] = templine[0];
//                        mCorrectAnswers[1] = templine[1];
//                        mCorrectAnswers[2] = templine[2];
//                        mCorrectAnswers[3] = templine[3];
//                    }
//
//                } else{
//                    number =0;
//                    array++;
//                }
//
//            }
////            fileInputStream.close();
////            line = stringBuilder.toString();
////            bufferedReader.close();
//            scanner.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.i("questions",Arrays.toString(mQuestions));
//        Log.i("choices",Arrays.deepToString(mChoices));
//        Log.i("answers",Arrays.toString(mCorrectAnswers));
//        System.out.println(Arrays.deepToString(mChoices));
//        Log.i("answer0",mCorrectAnswers[0]);
//        Log.i("answer0",mCorrectAnswers[1]);
//        Log.i("answer0",mCorrectAnswers[2]);
//        Log.i("answer0",mCorrectAnswers[3]);
//
//
//    }
//
    private String mQuestions[] = {
            "Which part of the plant holds it in the soil?",
            "This part of the plant absorbs energy from the sun.",
            "This part of the plant attracts bees, butterflies and hummingbirds.",
            "The _______ holds the plant upright."

            };


    private String mChoices[][] = {
            {"Roots", "Stem", "Flower"},
            {"Fruit", "Leaves", "Seeds"},
            {"Bark", "Flower", "Roots"},
            {"Flower", "Leaves", "Stem"}
            };


    private String mCorrectAnswers[] = {"Roots", "Leaves", "Flower", "Stem"};


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
