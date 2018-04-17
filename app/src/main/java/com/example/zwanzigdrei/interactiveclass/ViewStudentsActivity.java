package com.example.zwanzigdrei.interactiveclass;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewStudentsActivity extends AppCompatActivity {

    // Get a reference to the database service
    private DatabaseReference dataCourse;
    private DatabaseReference stddataRef;

    private ArrayList<String> data = new ArrayList<>();

    private MyListAdaper myAdapter;

    private boolean mRunning = true;
    private Handler mHandler = new Handler();

    private Intent intent;
    private String week;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudents);

        intent = getIntent();
        week = intent.getStringExtra("week");

        // intent to get data of what week we are in
        dataCourse = FirebaseDatabase.getInstance().getReference().child("Subjects").child("Computer Systems Engineering").child(week);

        ListView lv = findViewById(R.id.listview);

        generateListContent();
        myAdapter = new MyListAdaper(this, R.layout.list_item, data);

        lv.setAdapter(myAdapter);
        mUpdater.run();

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

    // This checks firebase and stores users in a array list
    private void generateListContent() {
        dataCourse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> users = dataSnapshot.getChildren().iterator();
                while (users.hasNext()) {
                    DataSnapshot user = users.next();
                    String userID = user.child("studentID").getValue().toString();
                    //Toast.makeText(ViewStudentsActivity.this, "userid: "+userID, Toast.LENGTH_SHORT).show();
                    data.add("" + userID);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // adapter for creating listview of students
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
                viewHolder.title =  convertView.findViewById(R.id.list_item_text);
                viewHolder.button =  convertView.findViewById(R.id.list_item_btn);
                viewHolder.button.setText("Check Records");
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.title.setText(getItem(position));

            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // create intent to move to record activity
                    Intent recordInt = new Intent(ViewStudentsActivity.this, RecordActivity.class);
//                    Toast.makeText(ViewStudentsActivity.this, getItem(position), Toast.LENGTH_SHORT).show();
                    recordInt.putExtra("studentID",getItem(position));
                    recordInt.putExtra("week",week);
                    startActivity(recordInt);

                }
            });

            return convertView;
        }
    }
    public class ViewHolder {
        TextView title;
        Button button;
    }


}
