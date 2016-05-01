/*
 * Created by Alejandro Valenzuela Navarro
 * Copyright © 2016 makers. All rights reserved.
 *
 */



package com.makersupv.makers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;

import com.parse.ParseUser;

public class Login extends AppCompatActivity {

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

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            nextActivity();
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
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
