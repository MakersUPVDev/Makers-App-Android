/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

//Example activity

public class TestVerProyectos extends AppCompatActivity {

    private List<String> projects;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ver_proyectos);
        listView = (ListView) findViewById(R.id.listView);

        projects = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projects);
        listView.setAdapter(arrayAdapter);
    }


    public void populateView(View v){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Project"); //Query Project table
        query.setLimit(10); //Doing query limits is a good practice, for pagination use "skip(int n)"
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    arrayAdapter.clear(); //Clear the adapter
                    getAllNames(scoreList); //Add all Project names to the adapter
                    arrayAdapter.notifyDataSetChanged(); //Notify the adapter that all the data changed is ready to be shown
                } else {
                    Log.d("Projects", "Error: " + e.getMessage());
                }
            }
        });
    }

    //Iterate through all the list getting project names
    public void getAllNames(List<ParseObject> list){
        for(int i = 0; i < list.size(); i++){
            arrayAdapter.add(list.get(i).get("name").toString());
        }
    }
}
