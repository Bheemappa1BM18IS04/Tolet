package com.example.tolet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class add extends AppCompatActivity {
    FirebaseDatabase mdatabase;
    DatabaseReference mref;
    FirebaseStorage mstorage;
    EditText colony,pincode,city,state,rent,advance,tc,area;
    ImageButton imageButton;
    Button button;
    private  static  final int Gallery_Code=1;
    Uri imageuri=null;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        colony=findViewById(R.id.colony);
        pincode=findViewById(R.id.pincode);
        city=findViewById(R.id.city);
        rent=findViewById(R.id.rent);
        state=findViewById(R.id.state);
        advance=findViewById(R.id.advance);
        area=findViewById(R.id.buildingname);

        tc=findViewById(R.id.condictionds);
        imageButton=findViewById(R.id.image);
        button=findViewById(R.id.button);
        mdatabase=FirebaseDatabase.getInstance();
        mref=mdatabase.getReference().child("xyz");
        mstorage=FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(intent,Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && requestCode==RESULT_OK)
        {
            imageuri=data.getData();
            imageButton.setImageURI(imageuri);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin=pincode.getText().toString().trim();
                String st=state.getText().toString().trim();
                String rt=rent.getText().toString().trim();
                if(!(pin.isEmpty() && st.isEmpty() && imageuri!=null))
                {
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath=mstorage.getReference().child("imagepost").child(imageuri.getLastPathSegment());
                    filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    DatabaseReference newpost=mref.push();
                                    newpost.child("pincode").setValue(pin);
                                    newpost.child("State").setValue(st);
                                   newpost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                }
                            });
                        }
                    });

                }

            }
        });
    }
}