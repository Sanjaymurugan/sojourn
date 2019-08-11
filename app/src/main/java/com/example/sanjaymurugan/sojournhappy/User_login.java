package com.example.sanjaymurugan.sojournhappy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User_login extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    EditText email,password;
    TextView create;
    Button login;
    FirebaseAuth.AuthStateListener auth;
    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(auth);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        email=(EditText)findViewById(R.id.usernametv);
        password=(EditText)findViewById(R.id.passwordtv);
        login=(Button)findViewById(R.id.loginbtn);
        create=(TextView)findViewById(R.id.sign);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        create.setOnClickListener(this);
        auth=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                    startActivity(new Intent(User_login.this,Home.class));
            }
        };
    }
    private void signin(String mailid, String pswd) {
        firebaseAuth.signInWithEmailAndPassword(mailid, pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "sign in problem", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
                if(TextUtils.isEmpty(email.getText().toString())&&TextUtils.isEmpty(password.getText().toString())){
                    String mailid = email.getText().toString().trim();
                    String pswd = password.getText().toString().trim();
                    progressDialog.setMessage("loging in");
                    progressDialog.show();
                    signin(mailid, pswd);
                }
                break;
            case R.id.sign:
                startActivity(new Intent(User_login.this,create_user.class));
        }
    }
}
