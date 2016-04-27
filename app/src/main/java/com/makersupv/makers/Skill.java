/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import com.parse.*;

@ParseClassName("Skill")
public class Skill extends ParseObject{

    public Skill() {
        //Default constructor... Not used, just for Parse
    }

    public String getObjectIdParse(){
        return getString("objectId");
    }

    public String getSkillName() {
        return getString("name");
    }

    public void setSkillName(String skillName) {
        put("name", skillName);
    }

    public ParseFile getIcon() {
        return getParseFile("icon");
    }

    public void setIcon(ParseFile image) {
        put("icon", image);
    }
}
