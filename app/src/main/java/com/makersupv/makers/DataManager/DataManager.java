/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.DataManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.makersupv.makers.Adapters.UploadImagesAdapter;
import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public interface SkillsCallback{
        void doneSkills(List<Skill> skills);
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

    //Use this method to convert a List<Bitmap> into a List<Image>
    public List<Image> createImageList(List<Bitmap> bitmaps){
        List<Image> parseImages = new ArrayList<>();
        for (Bitmap x : bitmaps){
            Image image = new Image();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            x.compress(Bitmap.CompressFormat.PNG, 100, stream);
            ParseFile parseFile = new ParseFile(stream.toByteArray());
            image.setParseImage(parseFile);
            parseImages.add(image);
        }
        return parseImages;
    }

    public void uploadImage(Context context, final UploadImagesAdapter.Item item, Uri imageUri, List<Image> listOfImages) throws IOException {
        InputStream iStream =   context.getContentResolver().openInputStream(imageUri);
        byte[] inputData = DataManager.getInstance().getBytes(iStream);
        ParseFile parseFile = new ParseFile(inputData);
        Image image = new Image();
        image.setParseImage(parseFile);
        listOfImages.add(image);
        image.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    item.progressBar.setVisibility(View.GONE);
                    item.projectUploadImageView.setVisibility(View.VISIBLE);
                } else {

                }
            }
        });
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public void getAllSkills(final SkillsCallback callback){
        ParseQuery<Skill> query = ParseQuery.getQuery("Skill");
        query.findInBackground(new FindCallback<Skill>() {
            @Override
            public void done(List<Skill> skills, com.parse.ParseException e) {
                if (e == null)
                    callback.doneSkills(skills);
                else
                    Log.d("Error", e.getMessage());
            }
        });
    }

}