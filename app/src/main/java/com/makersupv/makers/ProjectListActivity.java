/*
 *  Created by Iván José Martín García
 *  Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ProjectListActivity extends AppCompatActivity {

    private ListView projectListView;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        projectListView = (ListView) findViewById(R.id.projectListView);

        projectAdapter = new ProjectAdapter(this);

        projectListView.setAdapter(projectAdapter);

        projectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = projectAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("projectId", project.getObjectId());
                Intent intent = new Intent(ProjectListActivity.this, ProjectDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
