package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class image extends AppCompatActivity {
    private StorageReference mStorageRef;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String imguri= getIntent().getStringExtra("image");
         im = (ImageView)findViewById(R.id.full_image);
        mStorageRef = FirebaseStorage.getInstance().getReference("images");
        StorageReference sr = mStorageRef.child(imguri);
        sr.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bt = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                im.setImageBitmap(bt);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });

    }
}
