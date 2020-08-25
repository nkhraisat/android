package com.example.user.baudata;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class spec extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec);
        Spinner major=(Spinner) findViewById(R.id.major);
       final  TextView tex = (TextView) findViewById(R.id.tex);
       final Button plan1 =(Button) findViewById(R.id.plan);
       final Intent nn=new Intent(Intent.ACTION_VIEW);
        major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0 ) {
                    tex.setText("no thing selected..");

                }
                else if (position==1) {
                    tex.setText("softwereengineer is a person who applies the principles of software engineering to the design, development, maintenance, testing.");
                    nn.setData(Uri.parse("https://www.bau.edu.jo/PlansPdf/staticPlans/5.pdf"));

                }else if (position==2) {
                    tex.setText(" Computer science Specializations and courses teach software engineering and design, algorithmic thinking, human-computer interaction,\nhttps://www.bau.edu.jo/PlansPdf/staticPlans/3.pdf ");
                    nn.setData(Uri.parse("https://www.bau.edu.jo/PlansPdf/staticPlans/3.pdf "));

                }else if (position==3) {
                    tex.setText("The specialization(CIS)is one of the most important disciplines in information technology and computeThis specialization combines the various disciplines of computer science and the organizational and administrative fields");
                    nn.setData(Uri.parse("https://www.bau.edu.jo/PlansPdf/staticPlans/4.pdf"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       plan1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(nn);
           }
       });
    }
}
