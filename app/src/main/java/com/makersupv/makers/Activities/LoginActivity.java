/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */



package com.makersupv.makers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.makersupv.makers.R;
import com.parse.LogInCallback;
import com.parse.ParseException;

import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btLogin;

    String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            nextActivity();
                            Log.d("LOGIN", "ACCOMPLISHED");
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Log.d("LOGIN", e.getMessage());
                        }
                    }
                });

            }
        });





    }

    public void nextActivity(){
    //      Intent i = new Intent(this,SIGUIENTEACTIVIDAD.class);
    //      startActivity(i);
    //      finish();
    }

}
