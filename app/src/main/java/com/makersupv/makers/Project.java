/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import com.parse.*;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ParseClassName("Project")
public class Project extends ParseObject {

    public Project() {
        //Default constructor... Not used, just for Parse
    }

    public String getObjectIdParse(){
        return getString("objectId");
    }

    public String getProjectName() {
        return getString("name");
    }

    public void setProjectName(String projectName) {
        put("name", projectName);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public User getCreator() {
        return (User) get("creator");
    }

    public void setCreator(User creator) {
        put("creator", creator);
    }

    public int getDuration() {
        return getInt("duration");
    }

    public void setDuration(int duration) {
        put("duration", duration);
    }

    public int getMembersLimit() {
        return getInt("members_limit");
    }

    public void setMembersLimit(int membersLimit) {
        put("members_limit", membersLimit);
    }

    public void setImages(Image[] images) {
        put("images", Arrays.asList(images));
    }

    public String getVideo() {
        return getString("video");
    }

    public void setVideo(String video) {
        put("video", video);
    }

    public Date getCreatedAt() {
        return getDate("createdAt");
    }

    public void setCreatedAt(Date createdAt) {
        put("createdAt", createdAt);
    }

    public Date getDateEndJoin() {
        return getDate("date_end_join");
    }

    public void setDateEndJoin(Date dateEndJoin) {
        put("date_end_join", dateEndJoin);
    }

    public JSONArray getSkills() {
        return getJSONArray("skills");
    }

    public void setSkills(Skill[] skills) {
        put("skills", Arrays.asList(skills));
    }
}
