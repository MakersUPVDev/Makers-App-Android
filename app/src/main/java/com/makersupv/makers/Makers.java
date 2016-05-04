/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import android.app.Application;

import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.makersupv.makers.Models.User;
import com.parse.Parse;
import com.parse.ParseObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Makers extends Application {

    private static String APPLICATION_ID = "theAppId";
    private static String CLIENT_KEY = "s0m3th1ngth4t1sh4rdt0gu3ss";

    @Override
    public void onCreate(){
        super.onCreate();

        //Set up Parse
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APPLICATION_ID)
                .clientKey(CLIENT_KEY)
                .server("http://1f0c7cd3e0.url-de-test.ws/parse/")
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
