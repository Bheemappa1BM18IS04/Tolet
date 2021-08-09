package com.example.tolet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {
    TextView textView,textView1;
    ImageView imageView1,imageView2,imageView3,imageView4;
    Button button,button2;
    ListView mylistview,list2;
    public  int counter=1;
    ArrayList<String> myarraylist=new ArrayList<String>();
  String[] months={"Id:1234 \n Name:mallesh \n Age:21 \n BP:Normal  "};


   String[] month={" ID:404 \n Name:Vinod \n Age:28 \n Bp:medium "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mylistview=findViewById(R.id.listview);
        list2=findViewById(R.id.listview1);



        imageView3=findViewById(R.id.imageView6);
        imageView4=findViewById(R.id.imageView5);

        ArrayAdapter<String> montharray=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,months);
        mylistview.setAdapter(montharray);
        ArrayAdapter<String> montharra=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,month);
        list2.setAdapter(montharra);


imageView3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent oder=new Intent(homepage.this,Chating.class);
      startActivity(oder);
      if(counter==1)
      {
          imageView3.setEnabled(false);
          Toast.makeText(homepage.this," Booking",Toast.LENGTH_SHORT).show();
      }
      else{
          Toast.makeText(homepage.this,"alredy Booked",Toast.LENGTH_SHORT).show();

      }


    }
});



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profiles:
                 Intent profiles=new Intent(homepage.this,about.class);
                 startActivity(profiles);
                return  true;
            case R.id.order:
                Toast.makeText(this, "oder id 123456789 \n owner Number:7026128061", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.tolet:
                Intent addtolet=new Intent(homepage.this,AddTolet.class);
                startActivity(addtolet);

                return  true;

            default: return super.onOptionsItemSelected(item);


        }

    }


 /*   @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        if(snapshot.getValue(String.class)!=null)
        {
            String key=snapshot.getKey();
            if(key.equals("first"))
            {
                String first=snapshot.getValue(String.class);
                textView1.setText(first);

            }
            if(key.equals("first"))
            {
                String second=snapshot.getValue(String.class);
                textView1.setText(second);

            }

        }

    }
*/





}