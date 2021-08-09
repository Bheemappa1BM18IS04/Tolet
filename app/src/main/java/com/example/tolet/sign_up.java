package com.example.tolet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity {

    EditText lname,fname,mobile,mail,password,cpassword;
    Button button1;
  DatabaseReference databaseReference;

  AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        lname=findViewById(R.id.lastname);
        fname=findViewById(R.id.firstname);
        mobile=findViewById(R.id.mobilenumber);
        mail=findViewById(R.id.mail);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.confirmpossword);
        //awesomeValidation.addValidation(this,R.id.confirmpossword, RegexTemplate.NOT_EMPTY,R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.mobilenumber,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);
        awesomeValidation.addValidation(this,R.id.mobilenumber,RegexTemplate.NOT_EMPTY,R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.lastname,RegexTemplate.NOT_EMPTY,R.string.invalid_lastname);
        awesomeValidation.addValidation(this,R.id.firstname,RegexTemplate.NOT_EMPTY,R.string.invalid_firstname);
        awesomeValidation.addValidation(this,R.id.mail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.password,RegexTemplate.NOT_EMPTY,R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.confirmpossword,RegexTemplate.NOT_EMPTY,R.string.invalid_confirmpossword);

        awesomeValidation.addValidation(this,R.id.confirmpossword,R.id.password,R.string.invalid_Confiraction_Password);

        button1=findViewById(R.id.button2);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bheema");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(awesomeValidation.validate())
                //{
                    insertdataintonew();
               // }

            }
        });
    }
    private  void insertdataintonew()
    {
        String firstname=fname.getText().toString();
        String lastname=lname.getText().toString();
        String phonenumber=mobile.getText().toString();
        String pwd=password.getText().toString();
        String cpwd=cpassword.getText().toString();
        String mailid=mail.getText().toString();

        customer customers=new customer(firstname,lastname,phonenumber,pwd,mailid);
        databaseReference.push().setValue(customers);
        Toast.makeText(sign_up.this,"Sign Up Succesfully ",Toast.LENGTH_SHORT).show();


    }
}