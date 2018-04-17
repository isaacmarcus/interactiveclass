package com.example.zwanzigdrei.interactiveclass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;

public class PDFviewActivity extends AppCompatActivity {

    private static Button submit;
    private static EditText editText;
    private static PDFView pdfView;
    private static Button download;

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    private File Dir = new File(path);

    private Intent intent;
    private String fileName;
    private String studentID,week,feedback;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        intent = getIntent();
        fileName = intent.getStringExtra("filename")+".pdf";

        this.bundle = intent.getExtras();
        week = bundle.getString("week");
        studentID = bundle.getString("studentID");

        submit = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        download = (Button) findViewById(R.id.download);
        pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset(fileName).load();


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadFromDropboxFromPath(path + "downloadFileFromDropbox", "ProjectSIC_Test_Folder/" + fileName);
            }
        });

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        feedback = editText.getText().toString();
                        submitFeedback(feedback);
                    }
                }
        );

        AndroidAuthSession session = buildSession();
        dropboxAPI = new DropboxAPI<AndroidAuthSession>(session);

        Dir.mkdirs();

    }

    private static DropboxAPI<AndroidAuthSession> dropboxAPI;
    private static final String APP_KEY = "uli22ywt0ycnpil";
    private static final String APP_SECRET = "m7ben3wso7vmes7";
    private static final String ACCESSTOKEN = "5wKAV011pZAAAAAAAAAAdx5pMAbuJoUSuU1YFh0qakKTulhvwfRWbOGMwJgvX5Jl";
    private DropboxAPI.UploadRequest request;
    private AndroidAuthSession buildSession()
    {
        AppKeyPair appKeyPair = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
        session.setOAuth2AccessToken(ACCESSTOKEN);
        return session;
    }
    public static String DropboxUploadPathFrom = "";
    public static String DropboxUploadName = "";
    public static String DropboxDownloadPathFrom = "";
    public static String DropboxDownloadPathTo = "";

    private void DownloadFromDropboxFromPath (String downloadPathTo, String downloadPathFrom)
    {
        DropboxDownloadPathTo = downloadPathTo;
        DropboxDownloadPathFrom = downloadPathFrom;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Download file ...", Toast.LENGTH_SHORT).show();
                Thread th = new Thread(new Runnable() {
                    public void run() {
                        File file = new File(DropboxDownloadPathTo + DropboxDownloadPathFrom.substring(DropboxDownloadPathFrom.lastIndexOf('.')));
                        if (file.exists()) file.delete();
                        try {
                            FileOutputStream outputStream = new FileOutputStream(file);
                            dropboxAPI.getFile(DropboxDownloadPathFrom, null, outputStream, null);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "File successfully downloaded.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                th.start();
            }
        });
    }

    private void submitFeedback(String feedback){
        //do something
        Toast.makeText(this, "Feedback has been submitted", Toast.LENGTH_SHORT).show();
        FirebaseDatabase.getInstance().getReference().child("Subjects").child("Computer Systems Engineering").child(week).child(studentID).child("feedback").setValue(feedback);
    }

}
