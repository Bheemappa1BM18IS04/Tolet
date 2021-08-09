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
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddTolet extends AppCompatActivity {
private ImageView imageView;
private Uri imageuri;
//Button button;
EditText house,road,state,city,pincode,rent,advance,tc;


 private  FirebaseStorage storage;
 private StorageReference storageReference;
 DatabaseReference databaseReference;
AwesomeValidation awesomeValidation;

Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_tolet);
            storage=FirebaseStorage.getInstance();
            storageReference=storage.getReference();
            awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);


        databaseReference=FirebaseDatabase.getInstance().getReference().child("PatientDB");


        imageView=findViewById(R.id.image);
        button=findViewById(R.id.insert);
        house=findViewById(R.id.editTextTextPersonName);
        road=findViewById(R.id.editTextTextPersonName2);
        state=findViewById(R.id.editTextTextPersonName3);
        city=findViewById(R.id.editTextTextPersonName4);
        pincode=findViewById(R.id.editTextTextPersonName5);
        rent=findViewById(R.id.editTextTextPersonName7);
        advance=findViewById(R.id.editTextTextPersonName6);
        tc=findViewById(R.id.editTextTextPersonName8);


        awesomeValidation.addValidation(this,R.id.editTextTextPersonName, RegexTemplate.NOT_EMPTY,R.string.invalid_House_name);
        awesomeValidation.addValidation(this,R.id.editTextTextPersonName2,RegexTemplate.NOT_EMPTY,R.string.invalid_road);
        awesomeValidation.addValidation(this,R.id.editTextTextPersonName3,RegexTemplate.NOT_EMPTY,R.string.invalid_State);
        awesomeValidation.addValidation(this,R.id.editTextTextPersonName4,RegexTemplate.NOT_EMPTY,R.string.invalid_city);

        awesomeValidation.addValidation(this,R.id.editTextTextPersonName5,RegexTemplate.NOT_EMPTY,R.string.invalid_Pincode);
        awesomeValidation.addValidation(this,R.id.editTextTextPersonName7,RegexTemplate.NOT_EMPTY,R.string.invalid_rent);
        awesomeValidation.addValidation(this,R.id.editTextTextPersonName6,RegexTemplate.NOT_EMPTY,R.string.invalid_advance);

        awesomeValidation.addValidation(this,R.id.editTextTextPersonName8,RegexTemplate.NOT_EMPTY,R.string.invalid_Condictions);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseimage();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate())
                {
                    insertdata();

                }


            }
        });

    }

    private  void insertdata()
    {
        String Buildling=house.getText().toString();
        String area=road.getText().toString();
        String State=state.getText().toString();
        String City=city.getText().toString();
        String Pincode=pincode.getText().toString();
        String Rent=rent.getText().toString();
        String Advance=advance.getText().toString();
        String Tremas=tc.getText().toString();

        Rooms room=new Rooms(Buildling,area,State,City,Pincode,Rent,Advance,Tremas);
        databaseReference.push().setValue(room);
    }
    private  void chooseimage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri=data.getData();
            imageView.setImageURI(imageuri);
            uploadPicture();

        }
    }

    private void uploadPicture() {
      final ProgressDialog pd=new ProgressDialog(this);
      pd.setTitle("Uploading imageing...");
      pd.show();

        final String randomkey= UUID.randomUUID().toString();

      //  Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        StorageReference riversRef = storageReference.child("images/"+randomkey);

        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        //Snackbar.make(findViewById(android.R.id.))
                        pd.dismiss();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        pd.dismiss();

                        Toast.makeText(getApplicationContext(),"Failed to upload",Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                //double Prpgresspercent=(100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
            //pd.setMessage("Progress"+(int)Prpgresspercent+"%");
            }
        });

    }
}