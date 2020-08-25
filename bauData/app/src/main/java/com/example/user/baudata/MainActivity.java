package com.example.user.baudata;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button avg=(Button) findViewById(R.id.avg);
        final Button sum=(Button) findViewById(R.id.sum);
        final Button about=(Button) findViewById(R.id.about);
        final Button des=(Button) findViewById(R.id.des);
        final Button spec=(Button) findViewById(R.id.spec);

    }

    public void onclick1(View v){
        Intent matrial=new Intent(this,summrises.class);

        startActivity(matrial);
    }
    public void btn(View view)
    {
        Intent about=new Intent(this,about.class);
        startActivity(about);
    }
    public void onclick7(View view)
    {
        Intent avg=new Intent(this,aveg.class);
        startActivity(avg);
    }
    public void desc(View v)
    {
        Intent descreption = new Intent(getApplicationContext(),description.class);
        startActivity(descreption);
    }
    public void sp (View v)
    {
        Intent in = new Intent(getApplicationContext(),spec.class);
        startActivity(in);
    }
    TextView tv=new (this);
//noor aldeen khraisat

}
