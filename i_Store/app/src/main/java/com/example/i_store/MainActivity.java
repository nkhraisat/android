package com.example.i_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password,fName,Lname;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String log_email;
    String log_pass;
     FirebaseAuth mAuth;
     String uid;
    Intent cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();



    }
    public void onclick_login(View view) {
        log_email=email.getText().toString();
        log_pass=password.getText().toString();
        if (log_email.isEmpty()) {
            email.setError("please enter your email!");
        }
            if (log_pass.isEmpty()) {
                password.setError("please enter your password!");
            }

             if(!log_email.isEmpty()&&!log_pass.isEmpty()) {
                 mAuth.signInWithEmailAndPassword(log_email, log_pass)
                         .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (task.isSuccessful()) {
                                     // Sign in success, update UI with the signed-in user's information
                                     FirebaseUser user = mAuth.getCurrentUser();
                                     Toast.makeText(MainActivity.this, "login successfully :)",
                                             Toast.LENGTH_SHORT).show();
                                      cat = new Intent(MainActivity.this, category.class);
                                     FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                             for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                                 register p =snapshot.getValue(register.class);
                                                 if(p.getEmail().equals(log_email))
                                                 {    uid=p.getUid();
                                                     cat.putExtra("nn",uid);


                                                           startActivity(cat);
                                                           finish();

                                             }}

                                         }

                                         @Override
                                         public void onCancelled(@NonNull DatabaseError databaseError) {

                                         }
                                     });

                                 } else {
                                     // If sign in fails, display a message to the user.
                                     Toast.makeText(MainActivity.this, "login faild :(",
                                             Toast.LENGTH_SHORT).show();
                                     // ...
                                 }

                                 // ...
                             }
                         });
             }


    }

    public void startssignup(View view) {
        Intent intent = new Intent( this,signup.class );
        startActivity( intent );
    }

    public void forgetpassword(View view) {
        Intent intent = new Intent( this,ForgetPassword.class );
        startActivity( intent );
    }
}
