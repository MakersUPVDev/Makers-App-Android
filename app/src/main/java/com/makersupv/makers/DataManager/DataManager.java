/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.DataManager;

import android.util.Log;

import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance = new DataManager();

    private DataManager(){}

    public static DataManager getInstance(){
        return instance;
    }

    public interface ProjectsCallback {
        void doneProjects(List<Project> projects, List<List<Image>> listOfImages, List<List<Skill>> listOfSkills);
    }

    public interface ProjectCallback {
        void doneProject(Project project);
    }

    public void getAllProjects(final ProjectsCallback callback){
        ParseQuery<Project> query = ParseQuery.getQuery("Project");
        query.addAscendingOrder("createdAt");
        query.include("images");
        query.include("skills");
        query.findInBackground(new FindCallback<Project>() {
            @Override
            public void done(List<Project> projects, com.parse.ParseException e) {
                if (e == null)
                    callback.doneProjects(projects, getArrayOfProjectImages(projects), getArrayOfProjectSkills(projects));
                else
                    Log.d("Error", e.getMessage());
            }
        });
    }

    public List<List<Image>> getArrayOfProjectImages(List<Project> projects){
        List<List<Image>> listOfImages = new ArrayList<>();
        for(int i = 0; i < projects.size(); i++){
            List<Image> projectImages = (List<Image>) (projects.get(i)).get("images");
            listOfImages.add(projectImages);
        }
        return listOfImages;
    }

    public List<List<Skill>> getArrayOfProjectSkills(List<Project> projects){
        List<List<Skill>> listOfSkills = new ArrayList<>();
        for(int i = 0; i < projects.size(); i++){
            List<Skill> projectSkills = (List<Skill>) (projects.get(i)).get("skills");
            listOfSkills.add(projectSkills);
        }
        return listOfSkills;
    }

    public void getProject(final ProjectCallback callback, String projectId){
        ParseQuery<Project> query = ParseQuery.getQuery("Project");
        query.include("skills");
        query.include("images");
        query.getInBackground(projectId, new GetCallback<Project>() {
            public void done(Project project, ParseException e) {
                if (e == null) {
                    callback.doneProject(project);
                } else {
                    Log.d("Error", e.getMessage());
                }
            }
        });
    }

}