package com.example.tolet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class userhomepage extends AppCompatActivity {
    TextView textView;
    public String a;

    private static final String[] patient=new String[]{"Name:mallesh  \n id:1234 bp:Normal \n age:21 current_Staus:ok", "Name:vinod  \n id:12345 bp:High \n age:28 Current_status:ok",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
        textView=findViewById(R.id.textView);


        AutoCompleteTextView autoCompleteTextView=findViewById(R.id.actv);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,patient);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);



    }
}