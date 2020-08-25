package com.example.i_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forget_password );

    }

    public void reset(View view) {
        Toast.makeText( this,"Password Reseted",Toast.LENGTH_LONG ).show();
        Intent intent = new Intent( getApplicationContext() , category.class );
        startActivity( intent );
    }

    public void reset1(View view) {
        Toast.makeText(this, "we send to your email a link to reset your password", Toast.LENGTH_SHORT).show();
    }
}
