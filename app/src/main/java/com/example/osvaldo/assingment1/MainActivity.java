package com.example.osvaldo.assingment1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
//firebase imports
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, passoword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        this.email = findViewById(R.id.usernameInput);
        this.passoword = findViewById(R.id.passwordInput);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    @Override
    protected void onStart() {
        super.onStart();
        this.checkAuthentication();
    }
    public void checkAuthentication() {
        FirebaseUser currentUser = mAuth.getInstance().getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
            return;
        }
    }
    public void Login(View view) {
        String userEmail = this.email.getText().toString();
        String userPassowrd = this.passoword.getText().toString();

        if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassowrd)){
            Toast.makeText(getApplicationContext(),"Email and Password field is required",Toast.LENGTH_SHORT).show();
            return;
        }
        this.mAuth.signInWithEmailAndPassword(userEmail, userPassowrd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Check Email and Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
    public void needHelp(View view) {
        Intent intent = new Intent(this, Help.class);
        intent.putExtra("Help_info", "more information will ber display soon sdkbsjdvsdjvhgsvdjjh");
        startActivity(intent);
    }
}
