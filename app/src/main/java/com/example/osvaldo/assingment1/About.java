package com.example.osvaldo.assingment1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class About extends AppCompatActivity {
    private TextView abouttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.abouttext = findViewById(R.id.abouttext);
        TermsText about = new TermsText();
        this.abouttext.setText(about.aboutText());
        this.setToolbar();
    }
    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.Apptoolbar);
        String title = "About";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
