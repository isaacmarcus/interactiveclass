package mynamechef.dropboxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFviewActivity extends AppCompatActivity {

    public static Button submit;
    public static EditText editText;
    public static PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        Intent intent = getIntent();
        String fileName = intent.getStringExtra("filename")+".pdf";

        submit = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset(fileName).load();

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String feedback = editText.getText().toString();
                        submitFeedback(feedback);
                    }
                }
        );

    }

    private void submitFeedback(String feedback){
        //do something
    }

}
