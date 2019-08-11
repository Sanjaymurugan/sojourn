package com.example.sanjaymurugan.sojournhappy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class appadminlogintonext extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText mail,password;
    Button login;
    ImageView logologin;
    FirebaseAuth.AuthStateListener auth;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference mref;


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(auth);
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appadminlogintonext);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitle("Admin Login");
        mail = (EditText) findViewById(R.id.usernametv);
        password = (EditText) findViewById(R.id.passwordtv);
        progressDialog = new ProgressDialog(this);
        login = (Button) findViewById(R.id.loginbtn);
        logologin = (ImageView) findViewById(R.id.logoappadminpage);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/sojournhappy.appspot.com/o/SojournFinal2.png?alt=media&token=00cd2753-84b1-48d9-856f-87e5715db9a8").into(logologin);
        mref = FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        auth=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                    startActivity(new Intent(appadminlogintonext.this,appadminvisit.class));
            }
        };



//        FirebaseUser currentUser = auth.getCurrentUser();
//        updateUI(currentUser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mail.getText())) {
                    Toast.makeText(getApplicationContext(), "enter the mailid", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getApplicationContext(), "enter the password", Toast.LENGTH_SHORT).show();

                } else {
                    String mailid = mail.getText().toString().trim();
                    String pswd = password.getText().toString().trim();
                    progressDialog.setMessage("loging in");
                    progressDialog.show();
                    if(mailid.equals("tmsanjay99@gmail.com")&&pswd.equals("sanjay99")){
                        startActivity(new Intent(getApplicationContext(),appadminvisit.class));
                    }
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void signin(String mailid, String pswd) {
        firebaseAuth.signInWithEmailAndPassword(mailid,pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"sign in problem",Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(getApplicationContext(),appadminvisit.class));
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        startActivity(new Intent(appadminlogintonext.this,Home.class));
        this.finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.appadminlogintonext, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(appadminlogintonext.this,Home.class));
        } else if (id == R.id.nav_admin) {
            startActivity(new Intent(appadminlogintonext.this,appadminlogintonext.class));
        } else if (id == R.id.nav_emergency) {
            startActivity(new Intent(appadminlogintonext.this,Emergency.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(appadminlogintonext.this,Help.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(appadminlogintonext.this,About_us.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        this.finish();
        return true;
    }
}
