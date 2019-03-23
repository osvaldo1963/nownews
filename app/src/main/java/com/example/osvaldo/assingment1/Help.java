package com.example.osvaldo.assingment1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        this.setToolbar();
        this.setWigets();
    }

    private void setToolbar() {
        Toolbar toobar = (Toolbar)findViewById(R.id.Apptoolbar);
        setSupportActionBar(toobar);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(Color.parseColor("#D3590F"));
    }

    private void setWigets() {
        TextView textview = (TextView)findViewById(R.id.textarea);
        String helpinfo = getIntent().getStringExtra("Help_info");
        textview.setText(helpinfo);
    }

}
