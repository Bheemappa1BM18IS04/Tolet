package com.example.tolet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {

    ListView listView;

    String[] months;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView=findViewById(R.id.listview);
        months = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> montharray=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,months);
            listView.setAdapter(montharray);


    }
}