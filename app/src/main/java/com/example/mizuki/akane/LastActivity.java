package com.example.mizuki.akane;


import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LastActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);
    }

    public void click(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}