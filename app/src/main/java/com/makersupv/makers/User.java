/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import com.parse.ParseObject;

import java.util.Date;


public class User {

    private String objectId;
    private Image avatar;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Boolean isActive;
    private String interests;
    private String degree;
    private Date birthDate;
    private Date lastAccess;
    private Date registerDate;
    private UserSkill[] skills;

    public User(Image avatar, ParseObject parseObject, UserSkill[] skills) {
        this.avatar = avatar;
        this.userName = parseObject.getString("username");
        this.name = parseObject.getString("name");
        this.surname = parseObject.getString("surname");
        this.email = parseObject.getString("email");
        this.password = parseObject.getString("password");
        this.isActive = parseObject.getBoolean("active");
        this.interests = parseObject.getString("interests");
        this.degree = parseObject.getString("degree");
        this.birthDate = parseObject.getDate("birthdate");
        this.lastAccess = parseObject.getDate("last_access");
        this.registerDate = parseObject.getDate("register_date");
        this.skills = skills;
    }

    public User(Image avatar, String userName, String name, String surname, String email, String password, Boolean isActive,
                String interests, String degree, Date birthDate, Date lastAccess, Date registerDate, UserSkill[] skills) {
        this.avatar = avatar;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.interests = interests;
        this.degree = degree;
        this.birthDate = birthDate;
        this.lastAccess = lastAccess;
        this.registerDate = registerDate;
        this.skills = skills;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public UserSkill[] getSkills() {
        return skills;
    }

    public void setSkills(UserSkill[] skills) {
        this.skills = skills;
    }
}
