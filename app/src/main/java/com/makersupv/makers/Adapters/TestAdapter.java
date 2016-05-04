/*
 *  Created by Iván José Martín García
 *  Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.makersupv.makers.Models.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TestAdapter extends BaseAdapter{

    ViewHolder holder;
    Context context;
    List<Image> listOfImages;

    static class ViewHolder {
        ImageView projectImageView;
    }

    public TestAdapter(Context context, List<Image> images){
        this.context = context;
        listOfImages = images;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(context, R.layout.item_project_image, null);
            holder = new ViewHolder();
            holder.projectImageView = (ImageView) view.findViewById(R.id.projectImageView);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        if(listOfImages != null && listOfImages.size() != 0){
            Picasso.with(context).load(listOfImages.get(position).getParseImage().getUrl()).into(holder.projectImageView);
        }
        else{
            holder.projectImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.makers_logo));
        }

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return listOfImages.size();
    }

    @Override
    public Image getItem(int index) {
        return getItem(index);
    }
}