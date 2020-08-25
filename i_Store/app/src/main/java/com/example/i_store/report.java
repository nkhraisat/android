package com.example.i_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class report extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Button btn=(Button)findViewById(R.id.btnr);
        et = (EditText)findViewById(R.id.et12);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          try {
              Toast.makeText(getApplicationContext(), "the product has reported", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                      "mailto", "norkres@gmail.com", null));
              intent.putExtra(Intent.EXTRA_SUBJECT, "PROBLEM IN ISTORE");
              intent.putExtra(Intent.EXTRA_TEXT,et.getText().toString() );
              startActivity(Intent.createChooser(intent, "Choose an Email client :"));
          }catch (Exception e){
              Toast.makeText(report.this, ""+e, Toast.LENGTH_SHORT).show();
          }
                }
        });

    }


    }

