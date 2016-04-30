/*
 *  Created by Iván José Martín García
 *  Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
