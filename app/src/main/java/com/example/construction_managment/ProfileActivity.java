package com.example.construction_managment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    Button logout;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    Button addproject;
    String name;
    ArrayList<SetterGetterClass> myclass;
    RecyclerView mrecycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mrecycleview = (RecyclerView)findViewById(R.id.myrecycleview);
        myclass = new ArrayList<>();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        addproject = (Button)findViewById(R.id.addprojectbtn);
        name = getIntent().getStringExtra("Name");
        logout = (Button)findViewById(R.id.logoutbtn);
        db = FirebaseFirestore.getInstance();
        loadData();
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        addproject.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i = new Intent(ProfileActivity.this,AddProject.class);
                i.putExtra("Name",name);
                startActivity(i);
            }
        });

    }

    private void loadData() {
        db.collection("yash")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               // Log.d(TAG, document.getId() + " => " + document.getData());
                                SetterGetterClass obj = new SetterGetterClass();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                                obj.setDate(dateFormat.format(document.getDate("StartingDate")));
                                obj.setDescription(document.getString("Status"));
                                obj.setTitle(document.getString("Name"));
                                myclass.add(obj);
                            }
                            mrecycleview.setLayoutManager(new StaggeredGridLayoutManager(2,1));
                            Myadapterclass myadapterclass = new Myadapterclass(getApplicationContext(), myclass);
                            mrecycleview.setAdapter(myadapterclass);
                        } else {
                           // Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


}
