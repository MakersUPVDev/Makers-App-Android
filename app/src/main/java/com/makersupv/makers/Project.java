/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import com.parse.ParseObject;

import java.util.Date;


public class Project {

    private String objectId;
    private String projectName;
    private String description;
    private User creator;
    private int duration;
    private User[] members;
    private int membersLimit;
    private Image[] images;
    private String video;
    private Date createdAt;
    private Date dateEndJoin;
    private Skill[] skills;

    public Project(ParseObject parseObject, Image[] images, Skill[] skills) {
        this.projectName = parseObject.getString("name");
        this.description = parseObject.getString("description");
        this.creator = (User) parseObject.get("owner");
        this.duration = parseObject.getInt("duration");
        this.membersLimit = parseObject.getInt("members_limit");
        this.images = images;
        this.video = parseObject.getString("video");
        this.createdAt = parseObject.getDate("createdAt");
        this.dateEndJoin = parseObject.getDate("date_end_join");
        this.skills = skills;
    }

    //This constructor must be used when retrieving data from Realm instead of parse
    public Project(String projectName, String description, User creator, int duration, int membersLimit,
                   Image[] images, String video, Date createdAt, Date dateEndJoin, Skill[] skills) {
        this.projectName = projectName;
        this.description = description;
        this.creator = creator;
        this.duration = duration;
        this.membersLimit = membersLimit;
        this.images = images;
        this.video = video;
        this.createdAt = createdAt;
        this.dateEndJoin = dateEndJoin;
        this.skills = skills;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User[] getMembers() {
        return members;
    }

    public void setMembers(User[] members) { this.members = members; }

    public int getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDateEndJoin() {
        return dateEndJoin;
    }

    public void setDateEndJoin(Date dateEndJoin) {
        this.dateEndJoin = dateEndJoin;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
}
