package com.example.osvaldo.assingment1;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//firebase imports
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private EditText email, password, conPassword;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        this.setToobar();
        this.setInitialiser();
        getWindow().setStatusBarColor(Color.parseColor("#D3590F"));
    }
    @Override
    protected void onStart() {
        super.onStart();
        //load anything on when view starts
    }
    private void setToobar() {
        Toolbar toolbar = findViewById(R.id.Apptoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setInitialiser() {
        this.email       = findViewById(R.id.usernameInput);
        this.password    = findViewById(R.id.passwordInput);
        this.conPassword = findViewById(R.id.conpasswordInput);
        this.auth        = FirebaseAuth.getInstance();
    }

    public void signUpAction(View view) {
        view.setEnabled(false);
        String Email       = this.email.getText().toString();
        String Password    = this.password.getText().toString();
        String Conpassword = this.conPassword.getText().toString();

        if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Conpassword)){
            Toast.makeText(getApplicationContext(),"Email and Password field is required",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Password.equals(Conpassword)) {
            auth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), Home.class));

                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            Toast.makeText(getApplicationContext(),"Password and confirm Password fields does not match "+Password+ " "+Conpassword,Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
