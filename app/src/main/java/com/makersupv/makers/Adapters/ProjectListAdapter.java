/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.makersupv.makers.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> implements View.OnClickListener {

    List<Project> projects;
    List<Image> images;
    List<Skill> skills;
    List<List<Skill>> listOfSkills;
    List<List<Image>> listOfImages;

    Context context;
    ViewHolder viewHolder;

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView projectName;
        public TextView projectDescription;
        public RecyclerView recyclerViewImage;
        public RecyclerView recyclerViewSkill;

        public ViewHolder(View view) {
            super(view);
            projectName = (TextView) view.findViewById(R.id.projectName);
            projectDescription = (TextView) view.findViewById(R.id.projectDescription);
            recyclerViewImage = (RecyclerView)view.findViewById(R.id.projectImagesRecyclerView);
            recyclerViewSkill = (RecyclerView)view.findViewById(R.id.projectSkillRecyclerView);
        }
    }

    public ProjectListAdapter(Context context, List<Project> projects, List<List<Image>> listOfImages, List<List<Skill>> listOfSkills){
        this.context = context;
        this.projects = projects;
        this.listOfImages = listOfImages;
        this.listOfSkills = listOfSkills;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project_list, parent, false);

        viewHolder = new ViewHolder(view);

        images = new ArrayList<>();

        skills = new ArrayList<>();

        return viewHolder;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.projectName.setText(projects.get(position).getProjectName());
        holder.projectDescription.setText(projects.get(position).getDescription());

        images.clear();
        skills.clear();

        ProjectImagesAdapter imageAdapter = new ProjectImagesAdapter(context, images);

        SkillsAdapter skillAdapter = new SkillsAdapter(context, skills);

        RecyclerView.LayoutManager layoutManagerImage = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerSkill = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerViewSkill.setLayoutManager(layoutManagerSkill);
        holder.recyclerViewSkill.setAdapter(skillAdapter);

        holder.recyclerViewImage.setLayoutManager(layoutManagerImage);
        holder.recyclerViewImage.setAdapter(imageAdapter);

        imageAdapter.notifyDataSetChanged();
        skillAdapter.notifyDataSetChanged();


        if(listOfImages != null && listOfImages.size() != 0){
            images.addAll(listOfImages.get(position));
        }

        if(listOfSkills != null && listOfSkills.size() != 0 && listOfSkills.get(position) != null){
            skills.addAll(listOfSkills.get(position));
        }

        imageAdapter.notifyDataSetChanged();

        if (skills.size() == 0){
            holder.recyclerViewSkill.setVisibility(View.GONE);
        }else {
            holder.recyclerViewSkill.setVisibility(View.VISIBLE);
            skillAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @Override
    public long getItemId(int position) {return position;}

}
