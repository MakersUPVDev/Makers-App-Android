/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makersupv.makers.Adapters.ProjectImagesAdapter;
import com.makersupv.makers.Adapters.SkillsAdapter;
import com.makersupv.makers.DataManager.DataManager;
import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.makersupv.makers.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsActivity extends AppCompatActivity {

    Context context;

    private String projectId;
    private List<Image> parseImages;
    private List<Skill> parseSkills;

    private ImageView projectImageView;
    private TextView projectDescriptionTextView;

    private RecyclerView skillsRecyclerView;
    private RecyclerView imagesRecyclerView;

    private SkillsAdapter skillsAdapter;
    private ProjectImagesAdapter projectImagesAdapter;

    private RecyclerView.LayoutManager layoutManagerSkills;
    private RecyclerView.LayoutManager layoutManagerImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        context = getApplicationContext();

        projectImageView = (ImageView) findViewById(R.id.projectImageView);

        projectDescriptionTextView = (TextView) findViewById(R.id.projectDescription);

        skillsRecyclerView = (RecyclerView) findViewById(R.id.skillsDetailRecyclerView);
        imagesRecyclerView = (RecyclerView) findViewById(R.id.imagesDetailRecyclerView);

        parseImages = new ArrayList<>();
        parseSkills = new ArrayList<>();

        layoutManagerSkills = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerImages = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        skillsAdapter = new SkillsAdapter(this, parseSkills);
        projectImagesAdapter = new ProjectImagesAdapter(this, parseImages);

        skillsRecyclerView.setAdapter(skillsAdapter);
        skillsRecyclerView.setLayoutManager(layoutManagerSkills);

        imagesRecyclerView.setAdapter(projectImagesAdapter);
        imagesRecyclerView.setLayoutManager(layoutManagerImages);

        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("projectId");

        DataManager.getInstance().getProject(new DataManager.ProjectCallback(){

            @Override
            public void doneProject(final Project project) {

                parseSkills.clear();
                parseImages.clear();

                List<Image> images = (List<Image>) project.get("images");
                List<Skill> skills = (List<Skill>) project.get("skills");

                if(project.get("images") != null && images.size()!= 0) {
                    parseImages.addAll(images);
                }
                else{
                    imagesRecyclerView.setVisibility(View.GONE);
                }

                if (project.get("skills") != null && skills.size() != 0 ) {
                    parseSkills.addAll(skills);
                }
                else{
                    skillsRecyclerView.setVisibility(View.GONE);
                }

                projectImagesAdapter.notifyDataSetChanged();
                skillsAdapter.notifyDataSetChanged();

                Handler mainHandler = new Handler(context.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(parseImages != null && parseImages.size() > 0){
                            Glide.with(context)
                                    .load(parseImages.get(0).getParseImage().getUrl())
                                    .placeholder(R.mipmap.makers_logo)
                                    .into(projectImageView);
                        } else {
                            projectImageView.setImageResource(R.mipmap.makers_logo);
                        }

                        collapsingToolbarLayout.setTitle(project.getProjectName());
                        projectDescriptionTextView.setText(project.getDescription());

                        skillsAdapter.notifyDataSetChanged();
                        projectImagesAdapter.notifyDataSetChanged();

                    }
                });

            }
        }, projectId);
    }

}