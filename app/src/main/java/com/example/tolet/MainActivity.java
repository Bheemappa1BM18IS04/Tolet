package com.example.tolet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    Button buttton1, button2, btn;
    EditText phoneNumber, password, editText3;
    TextView textView, textView2;



    AwesomeValidation awesomeValidation;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.admin);
        phoneNumber = findViewById(R.id.moibile);
        password = findViewById(R.id.password);
        textView2 = findViewById(R.id.warning);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        btn = findViewById(R.id.textView);
        buttton1 = findViewById(R.id.button);
        awesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.moibile, "[5-9]{1}[0-9]{9}", R.string.invalid_mobile);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,profile.class);
                startActivity(intent2);
            }
        });
    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userEnterphone=phoneNumber.getText().toString().trim();
            String userEnterpass=password.getText().toString().trim();
            if(TextUtils.isEmpty(userEnterphone))
            {
                //Toast.makeTthis,"Please Enter the password ",Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this,"please ENTER THE PASSWORD ",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(userEnterpass))
            {
                Toast.makeText(MainActivity.this,"please eNTER THE PASSWORD ",Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Admin");

            Query checkuser=databaseReference.orderByChild("mobile").equalTo(userEnterphone);
            checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists())
                    {
                        String passwordDB=snapshot.child(userEnterphone).child("password").child("password").getValue(String.class);
                        if(passwordDB.equals(userEnterpass))
                        {
                            //Intent intent=new Intent(MainActivity.this,homepage.class);


                            Intent intent1=new Intent(getApplicationContext(),homepage.class);

                            startActivity(intent1);
                        }
                        else{
                            password.setError("Wroung password ");
                            password.requestFocus();

                        }
                    }
                    else{phoneNumber.setError("no such number");

                    phoneNumber.requestFocus();
                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    });
}
}

     /*   private Boolean validateUsername()
        {
            String val=editText1.getText().toString();

            if(val.isEmpty())
            {
                editText1.setError("Please Enter the User name ");
                return false;


            }else{
                editText1.setError(null);
               // editText1.setErrorEnabled(false);
                return true;
            }

        }

      */


      /*  private Boolean validatePassord(){
            String val=editText2.getText().toString();
            if(val.isEmpty())
            {
                editText2.setError("Please Enter the valied Passrod ");
                return false;

            }else{
                editText2.setError("Cannot Empty ");
                //editText2.setErrorEnabled(false);

                return true;

            }
    }
        public void loginuser(View view){
            if(!validateUsername() | !validatePassord()){
                return;

            }
            else{
                isUser();
            }
        }



    /*

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singupage=new Intent(MainActivity.this,sign_up.class);
                startActivity(singupage);
            }
        });
        */
       /* buttton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(awesomeValidation.validate()) {
                  validate(editText1.getText().toString(), editText2.getText().toString()); }

            }
        });



  /*  private  void validate(String mobile,String password)
    {
        if((mobile.equals("8050358644")) && (password.equals("123456")))
        {
            Intent intenthomepage=new Intent(MainActivity.this,homepage.class);
            startActivity(intenthomepage);
        }
        else{
            counter--;
            textView2.setText("Wroung Password! \n No of attempts Reaiming :\t "+String.valueOf(counter));
            if(counter==0)
                buttton1.setEnabled(false);
        }

    }

   */

   /* private void isUser() {
        String username=editText1.getText().toString().trim();
        String password=editText2.getText().toString().trim();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("admin");

        Query checkUser=databaseReference.orderByChild("mobile").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String passwordDB=snapshot.child(password).child("password").getValue(String.class);


                    if(passwordDB.equals(password))
                    {
                        // String passwordDB=snapshot.child(password).child("password").getValue(String.class);
                        Intent intent=new Intent(MainActivity.this,homepage.class);
                        startActivity(intent);
                    }
                    else{
                        //Toast.makeText(MainActivity.this, Toast.LENGTH_SHORT, "Wroung paaword ").show();
                        Toast.makeText(MainActivity.this,"Wroung Password",Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(MainActivity.this,"No Such Doctorserbhehb",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}

    */