package com.example.osvaldo.assingment1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Terms extends AppCompatActivity {
    private TextView termns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        this.termns = findViewById(R.id.termstext);
        TermsText termnsdata = new TermsText();
        this.termns.setText(termnsdata.termns());
        this.setToolbar();
    }
    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.Apptoolbar);
        String title = "Terms & Conditions";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
