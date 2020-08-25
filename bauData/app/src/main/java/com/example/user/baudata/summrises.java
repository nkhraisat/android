package com.example.user.baudata;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class summrises extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summrises);
        Button android=(Button) findViewById(R.id.android);
        Button web=(Button) findViewById(R.id.web);
        Button database=(Button) findViewById(R.id.database);

    }
    public void onclick3(View v)
    {
        Intent android=new Intent(Intent.ACTION_VIEW);
        android.setData(Uri.parse("https://drive.google.com/open?id=1vDXozC3P4eX24SUDkHAA0VE2nBmuPCG3"));
        startActivity(android);
    }
    public void onclick4(View v)
    {
        Intent web=new Intent(Intent.ACTION_VIEW);
        web.setData(Uri.parse("https://drive.google.com/open?id=1vlP6vuzCin4mnZq2CRcCUybqwqN3_BeB"));
        startActivity(web);
    }
    public void onclick5(View v)
    {
        Intent database=new Intent(Intent.ACTION_VIEW);
        database.setData(Uri.parse("https://drive.google.com/open?id=1OAkP03-oqnU6D-_XcAUMxr0m5lTcANIl"));
        startActivity(database);
    }

}
//noor aldeen khraisat
