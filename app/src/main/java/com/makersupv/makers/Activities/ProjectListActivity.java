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

import java.util.List;

public class ProjectListActivity extends AppCompatActivity {

    private Context context;

    ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.loading_content);
        progressDialog.setCancelable(false);
        progressDialog.show();

        context = getApplicationContext();


        DataManager.getInstance().getAllProjects(new DataManager.ProjectsCallback() {
            @Override
            public void doneProjects(final List<Project> projectList, final List<List<Image>> images, final List<List<Skill>> skills) {
                Handler mainHandler = new Handler(context.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        recyclerView = (RecyclerView) findViewById(R.id.projectsRecyclerView);

                        adapter = new ProjectListAdapter(context, projectList, images, skills);

                        layoutManager = new LinearLayoutManager(context);

                        recyclerView.setAdapter(adapter);

                        recyclerView.setLayoutManager(layoutManager);

                        recyclerView.addOnItemTouchListener(
                                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        Project project = projectList.get(position);
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
