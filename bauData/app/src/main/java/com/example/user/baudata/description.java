package com.example.user.baudata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.*;

public class description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        TextView tv=(TextView)findViewById(R.id.tv1);
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.app));
        String allText = "";
        while(scan.hasNextLine())
        {    String line = scan.nextLine();
            allText += line;
        }
        tv.setText(allText);
        scan.close();
    }
}
//noor aldeen khraisat
