/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import com.parse.ParseObject;


public class Skill {

    private String objectId;
    private String skillName;
    private Image icon;

    public Skill(ParseObject skillName, Image icon) {
        //this.objectId = objectId;
        this.skillName = skillName.getString("name");
        this.icon = icon;
    }


    //This constructor will be used when retrieving data from Realm instead of parse
    public Skill(String skillName, Image icon) {
        //this.objectId = objectId;
        this.skillName = skillName;
        this.icon = icon;
    }


    public String getObjectId() {return objectId;}

    public void setObjectId(String objectId) {this.objectId = objectId;}

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
