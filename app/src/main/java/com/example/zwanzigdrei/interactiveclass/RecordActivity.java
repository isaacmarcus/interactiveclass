package com.example.zwanzigdrei.interactiveclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    // Get a reference to the database service
    private DatabaseReference dataCourse;

    private ArrayList<String> data = new ArrayList<>();

    private MyListAdaper myAdapter;

    // initializer for "updating content"
    private boolean mRunning = true;
    private Handler mHandler = new Handler();

    // initializer for intents
    private Intent intent;
    private String week;
    private String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        intent = getIntent();
        week = intent.getStringExtra("week");
        studentID = intent.getStringExtra("studentID");

        dataCourse = FirebaseDatabase.getInstance().getReference().child("Subjects").child("Computer Systems Engineering").child(week).child(studentID);
        ListView lv = findViewById(R.id.listrecord);

        generateListContent();
        myAdapter = new MyListAdaper(this, R.layout.list_record, data);
        lv.setAdapter(myAdapter);
        mUpdater.run(); // to update list

    }

    // This runnable helps to check firebase every 500ms to update listview
    Runnable mUpdater = new Runnable() {
        @Override
        public void run() {
            // check if still in focus
            if (!mRunning) return;

            // update list view
            myAdapter.notifyDataSetChanged();

            // schedule next run
            mHandler.postDelayed(this, 500); // set time here to refresh views
        }
    };

    // Resumes Checking
    @Override
    protected void onResume() {
        super.onResume();
        mRunning = true;
        // start first run by hand
        mHandler.post(mUpdater);
    }

    // Pauses Checking
    @Override
    protected void onPause() {
        super.onPause();
        mRunning= false;
    }

    // This checks firebase and stores records in an array list
    private void generateListContent() {
        dataCourse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> records = dataSnapshot.getChildren().iterator();
                while (records.hasNext()) {
                    DataSnapshot record = records.next();
                    String key = record.getKey().toString();
                    String value = record.getValue().toString();
                    data.add("" + key + ": " + value + "\n");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // Adapter for listview that lists records
    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder mainViewholder;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title =  convertView.findViewById(R.id.list_record_text);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder {
        TextView title;
    }

}
