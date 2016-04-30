/*
 *  Created by Iván José Martín García
 *  Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;

public class ProjectAdapter extends ParseQueryAdapter<Project> {

    Context context;

    public ProjectAdapter(Context context){
        super(context, new ParseQueryAdapter.QueryFactory<Project>() {
            public ParseQuery<Project> create() {
                ParseQuery query = new ParseQuery("Project");
                query.include("images");
                return query;
            }
        });
        this.context = context;
    }

    @Override
    public View getItemView(Project project, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(getContext(), R.layout.item_project_list_layout, null);
        }

        super.getItemView(project, view, parent);

        ParseImageView parseImageView = (ParseImageView) view.findViewById(R.id.parseImageView);
        ArrayList<Image> parseImages = (ArrayList<Image>) project.get("images");

        if(parseImages != null && parseImages.size() != 0){
            parseImageView.setParseFile(((ParseFile) parseImages.get(0).get("image")));
            parseImageView.loadInBackground();
        }
        else{
            parseImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.makers_logo));
        }

        TextView projectNameTextView = (TextView) view.findViewById(R.id.projectName);
        projectNameTextView.setText(project.getProjectName());

        TextView projectDescriptionTextView = (TextView) view.findViewById(R.id.projectDescription);
        projectDescriptionTextView.setText(project.getDescription());

        return view;
    }

}
