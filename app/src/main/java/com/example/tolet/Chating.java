package com.example.tolet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chating extends AppCompatActivity {

    EditText editText1,editText2,editText3;
    Button button;
    DatabaseReference databaseReference;
AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        editText1=findViewById(R.id.card);
        editText2=findViewById(R.id.mmyy);
        editText3=findViewById(R.id.cvv);
        button=findViewById(R.id.order);
        awesomeValidation.addValidation(this,R.id.card,"[1-9]{4}[1-9]{4}[1-9]{4}$",R.string.Invalid_card_Number);

        awesomeValidation.addValidation(this,R.id.mmyy, "[1-9]{2}[0-9]{2}",R.string.invalid_monthYear);
        awesomeValidation.addValidation(this,R.id.cvv,"[0-9]{3}",R.string.invalid_cvv);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate())
                {
                    Toast.makeText(Chating.this,"Order Successfully",Toast.LENGTH_SHORT).show();

                    insertdata();
                }
                else
                {
                    //Toast.makeText(Chating.this,"Order Successfully",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
    private  void insertdata()
    {
        String card=editText1.getText().toString();
        String mm=editText2.getText().toString();
        String cvv=editText3.getText().toString();
        card oder=new card(card,mm,cvv);

        databaseReference.push().setValue(oder);
        Toast.makeText(Chating.this,"Booked Succesfully ",Toast.LENGTH_SHORT).show();
    }
}