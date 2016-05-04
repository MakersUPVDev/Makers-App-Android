/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers.Models;

//TODO

import com.makersupv.makers.Models.Skill;

public class UserSkill{

    private Skill skill;
    private int level;

    public UserSkill(Skill skill){
        this.skill = skill;
        this.level = 1;
    }

    public UserSkill(Skill skill, int level){
        this.skill = skill;
        this.level = level;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
