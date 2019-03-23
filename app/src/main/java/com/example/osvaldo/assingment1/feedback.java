package com.example.osvaldo.assingment1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class feedback extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        FragmentManager freamentmanager = getSupportFragmentManager();
        FragmentTransaction transaction = freamentmanager.beginTransaction();
        InputFragment input = new InputFragment();
        transaction.replace(R.id.feedcontent, input);
        transaction.commit();
    }
}
