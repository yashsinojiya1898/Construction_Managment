package com.example.construction_managment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddProject extends AppCompatActivity {
    FirebaseFirestore db;
    EditText projectname,clientname,duration,address,city,country;
    EditText startingdate;
    Button add;
    String name;
    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        name = getIntent().getStringExtra("Name");
        db = FirebaseFirestore.getInstance();
        projectname = (EditText)findViewById(R.id.projectname);
        clientname = (EditText)findViewById(R.id.clientname);
        duration = (EditText)findViewById(R.id.duration);
        address = (EditText)findViewById(R.id.address);
        city = (EditText)findViewById(R.id.city);
        country = (EditText)findViewById(R.id.country);
        startingdate = (EditText) findViewById(R.id.startingdate);
        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> project = new HashMap<>();
                project.put("Name",projectname.getText().toString());
                project.put("Client",clientname.getText().toString());
                project.put("Duration",Integer.parseInt(duration.getText().toString()));
                project.put("Address",address.getText().toString());
                project.put("City",city.getText().toString());
                project.put("Country",country.getText().toString());
                project.put("Status","In progress");

                format = new SimpleDateFormat("yyyy-MM-dd");

                project.put("StartingDate",getDateFromString(startingdate.getText().toString().trim()));



                db.collection(name).add(project)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                       // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Intent i = new Intent(AddProject.this,ProfileActivity.class);
                        startActivity(i);
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               // Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });



        final Calendar myCalendar = Calendar.getInstance();
//

    }

    public Date getDateFromString(String datetoSaved){

        try {
            Date date = format.parse(datetoSaved);
            return date ;
        } catch (ParseException e){
            return null ;
        }

    }
}
