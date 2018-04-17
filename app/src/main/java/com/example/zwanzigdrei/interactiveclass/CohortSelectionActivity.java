package mynamechef.dropboxtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CohortSelectionActivity extends AppCompatActivity {

    public static Button class1Btn;
    public static Button class2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cohort_selection);

        Intent intent = getIntent();
        final String week = intent.getStringExtra("week");

        class1Btn = (Button)findViewById(R.id.button18);
        class2Btn = (Button)findViewById(R.id.button19);


        class1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySlidesActivity("filename",week+"class1");

            }
        });

        class2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySlidesActivity("filename",week+"class2");

            }
        });
    }

    private void displaySlidesActivity(String key, String value){
        Intent myIntent = new Intent(this, PDFviewActivity.class);
        myIntent.putExtra(key, value);
        startActivity(myIntent);
    }

}
