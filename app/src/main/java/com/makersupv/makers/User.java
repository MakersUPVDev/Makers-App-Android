/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import com.parse.*;

import java.util.Date;

@ParseClassName("User")
public class User extends ParseObject{

    public User() {
        //Default constructor... Not used, just for Parse
    }

    public String getObjectIdParse(){
        return getString("objectId");
    }

    public ParseFile getAvatar() {
        return getParseFile("avatar");
    }

    public void setAvatar(ParseFile image) {
        put("avatar", image);
    }

    public String getUserName() {
        return getString("username");
    }

    public void setUserName(String userName) {
        put("username", userName);
    }

    public String getFirstName() {
        return getString("name");
    }

    public void setFirstName(String name) {
        put("name", name);
    }

    public String getSurname() {
        return getString("surname");
    }

    public void setSurname(String surname) {
        put("surname", surname);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        put("email", email);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public Boolean getActive() {
        return getBoolean("active");
    }

    public void setActive(Boolean active) {
        put("active", active);
    }

    public String getInterests() {
        return getString("interests");
    }

    public void setInterests(String interests) {
        put("interests", interests);
    }

    public String getDegree() {
        return getString("degree");
    }

    public void setDegree(String degree) {
        put("degree", degree);
    }

    public Date getBirthDate() {
        return getDate("birthdate");
    }

    public void setBirthDate(Date birthDate) {
        put("birthdate", birthDate);
    }

    public Date getLastAccess() {
        return getDate("last_access");
    }

    public void setLastAccess(Date lastAccess) {
        put("last_access", lastAccess);
    }

    public Date getRegisterDate() {
        return getDate("createdAt");
    }

    public void setRegisterDate(Date registerDate) {
        put("createdAt", registerDate);
    }

    //TODO UserSkill
    /**

     public  getSkills() {
     return skills;
     }

     public void setSkills(UserSkill[] skills) {
     this.skills = skills;
     }
     */
}
