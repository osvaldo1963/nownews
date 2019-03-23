package com.example.osvaldo.assingment1;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class Profile extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText pass;
    FirebaseUser user;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setToolbar();
        this.setInitialer();
        this.checkUser();
    }
    private void setInitialer() {
        this.email = findViewById(R.id.email);
        this.pass = findViewById(R.id.password);
        this.user = FirebaseAuth.getInstance().getCurrentUser();
        this.submitBtn = findViewById(R.id.submitBtn);
        this.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton();
            }
        });
    }
    private  void checkUser() {
        if(this.user != null) {
            for (UserInfo profile: this.user.getProviderData()) {
                String useremail = profile.getEmail();
                email.setText(useremail);
            }
        }
    }
    private void setToolbar() {
        Toolbar  toolbar = findViewById(R.id.Apptoolbar);
        String title = "Profile";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private  void submitButton() {
        if (this.email.getText().toString().isEmpty()){
            return;
        } else {
            this.user.updateEmail(this.email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        finish();
                    }
                }
            });
        }
        if(this.pass.getText().toString().isEmpty()) {
            return;
        } else {
            this.user.updatePassword(this.pass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                        }
                    });
        }

    }
}
