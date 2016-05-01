/*
 *  Created by Iván José Martín García
 *  Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProjectAdapter extends ParseQueryAdapter<Project> {

    ViewHolder holder;
    Context context;

    static class ViewHolder {
        TextView name;
        TextView description;
        ParseImageView projectImageView;
        ParseImageView skillImageView1;
        ParseImageView skillImageView2;
        ParseImageView skillImageView3;
    }

    public ProjectAdapter(Context context){
        super(context, new ParseQueryAdapter.QueryFactory<Project>() {
            public ParseQuery<Project> create() {
                ParseQuery query = new ParseQuery("Project");
                query.include("skills");
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
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.projectName);
            holder.description = (TextView) view.findViewById(R.id.projectDescription);
            holder.projectImageView = (ParseImageView) view.findViewById(R.id.projectImageView);
            holder.skillImageView1 = (ParseImageView) view.findViewById(R.id.skillImageView1);
            holder.skillImageView2 = (ParseImageView) view.findViewById(R.id.skillImageView2);
            holder.skillImageView3 = (ParseImageView) view.findViewById(R.id.skillImageView3);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        super.getItemView(project, view, parent);

        ArrayList<Image> parseImages = (ArrayList<Image>) project.get("images");

        if(parseImages != null && parseImages.size() != 0){
            ((ParseFile) parseImages.get(0).get("image")).cancel();
            holder.projectImageView.setParseFile(((ParseFile) parseImages.get(0).get("image")));
            holder.projectImageView.loadInBackground();
        }
        else{
            holder.projectImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.makers_logo));
        }

        ArrayList<Skill> parseSkills = (ArrayList<Skill>) project.get("skills");

        if(parseSkills != null && parseSkills.size() != 0){

            if (parseSkills.size() > 0){
                ((ParseFile) parseSkills.get(0).get("icon")).cancel();
                holder.skillImageView1.setParseFile((ParseFile) parseSkills.get(0).get("icon"));
                holder.skillImageView1.loadInBackground();
            }
            else {
                holder.skillImageView1.setImageResource(android.R.color.transparent);
            }

            if(parseSkills.size() > 1) {
                ((ParseFile) parseSkills.get(1).get("icon")).cancel();
                holder.skillImageView2.setParseFile((ParseFile) parseSkills.get(1).get("icon"));
                holder.skillImageView2.loadInBackground();

            }
            else {
                holder.skillImageView2.setImageResource(android.R.color.transparent);
            }

            if (parseSkills.size()  > 2) {
                ((ParseFile) parseSkills.get(2).get("icon")).cancel();
                holder.skillImageView3.setParseFile((ParseFile) parseSkills.get(2).get("icon"));
                holder.skillImageView3.loadInBackground();
            }
            else {
                holder.skillImageView3.setImageResource(android.R.color.transparent);
            }
        }
        else {
            holder.skillImageView1.setImageResource(android.R.color.transparent);
            holder.skillImageView2.setImageResource(android.R.color.transparent);
            holder.skillImageView3.setImageResource(android.R.color.transparent);
        }

        holder.name.setText(project.getProjectName());
        holder.description.setText(project.getDescription());

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
