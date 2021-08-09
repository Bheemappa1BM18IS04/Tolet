package com.example.tolet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormatSymbols;

public class about extends AppCompatActivity {
    ListView listView;
    String[] months={"Bheemappa \n c \n 8050358644 \n bheemappa.is18@bmsce.ac.in \n 123456"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
     listView=findViewById(R.id.listview);

     //   months = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> montharray=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,months);
        listView.setAdapter(montharray);

    }
}