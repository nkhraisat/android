package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update_password extends AppCompatActivity {
    EditText old,new_pass,confirm;
    String Email,user_id,pass;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    Button up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        old=(EditText)findViewById(R.id.old_password);
        new_pass=(EditText)findViewById(R.id.new_password);
        confirm=(EditText)findViewById(R.id.confirm_password);
        up=(Button)findViewById(R.id.update_pass);
         user = FirebaseAuth.getInstance().getCurrentUser();
        Email=user.getEmail();
        myRef = FirebaseDatabase.getInstance().getReference().child("database").child("users");
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        register p = snapshot.getValue(register.class);
                        try {
                            if (p.getEmail().equals(Email)) {
                                user_id = p.getUid();
                                pass=p.getPassword();


                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                        }


                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       try {
           up.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               try {
                   final String old_password, new_password, confirm_password;

                   old_password = old.getText().toString();

                   new_password = new_pass.getText().toString();
                   confirm_password=confirm.getText().toString();
                   if(old_password.equals(pass))
                   {
                   user.updatePassword(new_password)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()) {

                                       Toast.makeText(update_password.this, "successs", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
                   }else{old.setError("passowrd is wrong!");}}catch (Exception e){
                   Toast.makeText(update_password.this, ""+e, Toast.LENGTH_SHORT).show();}
                   Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_SHORT).show();
               }
           });

       }catch (Exception e){
           Toast.makeText(getApplicationContext(), "eror here "+e, Toast.LENGTH_SHORT).show();
    }


}}

