/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Makers extends Application {

    private static String APPLICATION_ID = "MGj3CWWXtBenD6OhI4IkKIN8DcfFD8GprJmZKnw5";
    private static String CLIENT_KEY = "03ZOM40IKIQrXVbQupsq77kreK0WcTRZcu2SvrDd";

    @Override
    public void onCreate(){
        super.onCreate();

        //Set up Parse
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APPLICATION_ID)
                .clientKey(CLIENT_KEY)
                .server("https://parseapi.back4app.com/")
                .build()
        );

        //Initialize all the Parse Classes
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Skill.class);
        ParseObject.registerSubclass(Project.class);
        ParseObject.registerSubclass(Image.class);

        //TODO
        //ParseObject.registerSubclass(UserSkill.class);


        //Set up Realm configuration
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
