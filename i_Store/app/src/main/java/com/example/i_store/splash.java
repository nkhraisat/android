package com.example.i_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class splash extends AppCompatActivity {
ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        bar=(ProgressBar)findViewById(R.id.bar);
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent( splash.this,MainActivity.class );
                startActivity(intent);
                finish();
            }
        },6000 );
    }

    public void login(View view) {
    }
    public void startProgress(View view) {

        bar.setProgress(0);
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                final int value = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bar.setProgress(value);

            }
        }

    }

}
