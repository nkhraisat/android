package com.example.user.baudata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class aveg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aveg);
        Button ma= (Button) findViewById(R.id.ma);

        Button c = (Button) findViewById(R.id.c);


        final EditText a1 = (EditText) findViewById(R.id.a1);

        final EditText a2 = (EditText) findViewById(R.id.a2);

        final EditText a3 = (EditText) findViewById(R.id.a3);

        final EditText a4 = (EditText) findViewById(R.id.a4);

        final EditText a5 = (EditText) findViewById(R.id.a5);
        a1.setText("0");
        a2.setText("0");
        a3.setText("0");
        a4.setText("0");
        a5.setText("0");


        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView Result = (TextView) findViewById(R.id.Result);

                double num1 = Double.parseDouble(a1.getText().toString());


                double num2 = Double.parseDouble(a2.getText().toString());


                double num3 = Double.parseDouble(a3.getText().toString());


                double num4 = Double.parseDouble(a4.getText().toString());


                double num5 = Double.parseDouble(a5.getText().toString());

                double sum= num1+num2+num3+num4+num5;
                double r=sum/5;



                Result.setText(r + "");

            }


        });
    }
    public void onclick6(View v)
    {
        Intent n=new Intent(this,MainActivity.class);
        startActivity(n);
    }
}