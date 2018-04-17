package com.example.zwanzigdrei.interactiveclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment {

    private String studentID;
    private String rank;

    private TextView studentidTV;
    private TextView rankTV;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        studentID = getArguments().getString("studentID");
        rank = getArguments().getString("rank");

        studentidTV = view.findViewById(R.id.studentid);
        rankTV = view.findViewById(R.id.rank);

        studentidTV.setText("ID: " + studentID);
        rankTV.setText("Rank: " + rank);

        return view;
    }



}


