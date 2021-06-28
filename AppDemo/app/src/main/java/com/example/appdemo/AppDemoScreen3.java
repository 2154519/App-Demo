package com.example.appdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AppDemoScreen3 extends AppCompatActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_demo_screen3);
        Switch();

    }

    public void Switch(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(AppDemoScreen3.this,MainActivity.class);
                startActivity(i);
            }
        }, 5000);

    }



}