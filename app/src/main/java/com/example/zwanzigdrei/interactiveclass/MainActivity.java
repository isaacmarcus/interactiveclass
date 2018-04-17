package com.example.zwanzigdrei.interactiveclass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.design.widget.BottomNavigationView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private String username;
    private String rank;
    private Bundle bundle;

    private BottomNavigationView bottomNavigationView;
    private static final String SELECTED_ITEM = "arg_selected_item";
    private int mSelectedItem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectFragment(item);
            return true;
        }
    };

    private void selectFragment(MenuItem item){
        Fragment frag = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = new MainFragment();
                frag.setArguments(bundle);
                break;
            case R.id.navigation_dashboard:
                frag = new CoursesFragment();
                frag.setArguments(bundle);
                break;
            case R.id.navigation_notifications:
                frag = new NotificationFragment();
                frag.setArguments(bundle);
                break;
        }

        mSelectedItem = item.getItemId();

        for (int i = 0; i< bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, frag, frag.getTag());
            ft.commit();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        bundle = getIntent().getExtras();
        username = bundle.getString("username");
        rank = bundle.getString("rank");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = bottomNavigationView.getMenu().findItem(mSelectedItem);
        }

        else {
            selectedItem = bottomNavigationView.getMenu().getItem(0);
        }
        selectFragment(selectedItem);

    }

    // Resume
    @Override
    protected void onResume() {
        super.onResume();
    }

    // Pause
    @Override
    protected void onPause() {
        super.onPause();
    }

    //logout on back pressed
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
