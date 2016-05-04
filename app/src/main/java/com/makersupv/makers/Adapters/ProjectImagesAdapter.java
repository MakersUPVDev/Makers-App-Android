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

import com.makersupv.makers.Models.Image;
import com.makersupv.makers.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectImagesAdapter extends RecyclerView.Adapter<ProjectImagesAdapter.ViewHolder> {

    List<Image> listOfImages;

    Context context;
    ViewHolder viewHolder;

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView projectImageView;

        public ViewHolder(View view) {
            super(view);
            projectImageView = (ImageView) view.findViewById(R.id.projectImageView);
        }
    }

    public ProjectImagesAdapter(Context context, List<Image> listOfImages){
        this.context = context;
        this.listOfImages = listOfImages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project_image, parent, false);

        viewHolder = new ViewHolder(view);

        viewHolder.projectImageView.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(listOfImages != null && listOfImages.size() > position && listOfImages.size() != 0){
                Picasso.with(holder.projectImageView.getContext()).load(listOfImages.get(position).getParseImage().getUrl()).into(holder.projectImageView);
        }
        else {
            holder.projectImageView.setImageResource(R.mipmap.makers_logo);
        }
    }

    @Override
    public int getItemCount() {
        return listOfImages.size();
    }

}
