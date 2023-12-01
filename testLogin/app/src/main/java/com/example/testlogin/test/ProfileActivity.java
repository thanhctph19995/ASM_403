 package com.example.testlogin.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import com.example.testlogin.R;
import com.example.testlogin.databinding.ActivityDsactivityBinding;
import com.example.testlogin.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

 public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog dialog;
    StorageReference storageReference;
    Uri gallerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(ProfileActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        FirebaseUser user = auth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("user" + "/" +user.getUid()+"/");
        storageReference = FirebaseStorage.getInstance().getReference("userImg" + "/" +user.getUid());

        
        
        uploadProfile();

    }

     private void uploadProfile() {
     }
 }