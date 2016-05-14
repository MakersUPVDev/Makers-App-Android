/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Skill;
import com.makersupv.makers.R;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

    List<Skill> listOfSkills;

    Context context;
    ViewHolder viewHolder;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView skillImage;
        public ImageView addedImage;

        public ViewHolder(View view) {
            super(view);
            skillImage = (ImageView) view.findViewById(R.id.skillImage);
            addedImage = (ImageView) view.findViewById(R.id.addedImageView);
        }
    }

    public SkillsAdapter(Context context, List<Skill> listOfSkills){
        this.context = context;
        this.listOfSkills = listOfSkills;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_skill, parent, false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (listOfSkills != null && listOfSkills.size() != 0){
            Glide.with(holder.skillImage.getContext())
                    .load(listOfSkills.get(position).getParseFile("icon").getUrl())
                    .placeholder(R.mipmap.makers_logo)
                    .into(holder.skillImage);
        }
    }

    @Override
    public int getItemCount() {
        return listOfSkills.size();
    }

}
