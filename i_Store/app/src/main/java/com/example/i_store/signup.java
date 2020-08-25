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

public class signup extends AppCompatActivity {
    EditText email, password, fname, lname, con_pass,num;
    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference myRef;
int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password_signup);
        con_pass = (EditText) findViewById(R.id.con_pass);
        num = (EditText) findViewById(R.id.phone_num);
        uid=0;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("database").child("users");
        mAuth = FirebaseAuth.getInstance();


    }





    public void startssignup2(View view) {
        FirebaseDatabase.getInstance().getReference().child("database").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                  try{  register p =snapshot.getValue(register.class);
                   if(uid<=Integer.parseInt(p.getUid()))
                       uid=Integer.parseInt(p.getUid())+1;}catch (Exception e){Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();}
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final String email_r, pass, firstN, lastN;
        email_r = email.getText().toString();
        pass = password.getText().toString();
        firstN = fname.getText().toString();
        lastN = lname.getText().toString();
        String c_pass = con_pass.getText().toString();
        if (email_r.isEmpty()) {
            email.setError("please enter your email!");

            if (pass.isEmpty()) {
                password.setError("please enter your password!");
                if (firstN.isEmpty()) {
                    fname.setError("please enter your first name!");
                    if (lastN.isEmpty()) {
                        lname.setError("please enter your last name!");
                        if (!email_r.contains("@")) {
                            email.setError("please enter a valid email!");
                            if (pass.length() < 8) {
                                email.setError("a password must contains at least 8 numbers or charchters!");
                                if (!c_pass.equals(pass)) {
                                    con_pass.setError("password not match!");
                                }
                                if (!num.getText().toString().isEmpty()) {
                                    con_pass.setError("please enter your phone number");
                                }

                            }
                        }
                    }
                }
            }
        }

        if (!email_r.isEmpty()) {

            if (!pass.isEmpty()) {
                if(!num.getText().toString().isEmpty())
                if (!firstN.isEmpty()) {
                    if (!lastN.isEmpty()) {
                        if (email_r.contains("@")) {
                            if (pass.length() >= 8) {
                                if (c_pass.equals(pass)) {

                                    try {
                                        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {


                                                        if (task.isSuccessful()) {
                                                            Log.d("my_store", "createUserWithEmail:success");
                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                            Intent cat = new Intent(signup.this, category.class);
                                                            cat.putExtra("nn",String.valueOf(uid));
                                                            startActivity(cat);
                                                            finish();
                                                            register r1 = new register(email_r,pass, firstN, lastN ,String.valueOf(uid),num.getText().toString(),"0","0");
                                                            myRef.child(String.valueOf(uid)).setValue(r1);
                                                            Toast.makeText(signup.this, "welcome to ISTORE family " + firstN + " ^-^", Toast.LENGTH_SHORT).show();


                                                        } else {
                                                            Log.d("my_store", "createUserWithEmail:failure", task.getException());
                                                            Toast.makeText(signup.this, "signup failed,\n please check to the internet conection and try again.",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }

                                                    }


                                                });
                                    } catch (Exception e) {
                                        Toast.makeText(signup.this, "something is wrong , please try again :).",
                                                Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }
                        }
                    }
                }
            }
        }
    }
}