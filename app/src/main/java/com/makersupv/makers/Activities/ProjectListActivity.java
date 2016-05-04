/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.makersupv.makers.Adapters.ProjectListAdapter;
import com.makersupv.makers.DataManager.DataManager;
import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.makersupv.makers.R;
import com.makersupv.makers.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {

    private Context context;

    ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Project> projects;
    private List<List<Image>> listOfImages;
    private List<List<Skill>> listOfSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.loading_content);
        progressDialog.show();

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.projectsRecyclerView);


        projects = new ArrayList<>();
        listOfImages = new ArrayList<>();
        listOfSkills = new ArrayList<>();

        adapter = new ProjectListAdapter(this, projects, listOfImages, listOfSkills);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        DataManager.getInstance().getAllProjects(new DataManager.ProjectsCallback() {
            @Override
            public void doneProjects(List<Project> projectList, List<List<Image>> images, List<List<Skill>> skills) {
                projects.addAll(projectList);
                listOfImages.addAll(images);
                listOfSkills.addAll(skills);
                Handler mainHandler = new Handler(context.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        recyclerView.addOnItemTouchListener(
                                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        Project project = projects.get(position);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("projectId", project.getObjectId());
                                        Intent intent = new Intent(ProjectListActivity.this, ProjectDetailsActivity.class);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                })
                        );
                        progressDialog.dismiss();
                    }
                });
            }
        });

    }
}
