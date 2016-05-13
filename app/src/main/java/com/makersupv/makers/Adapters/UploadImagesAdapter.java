/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.makersupv.makers.DataManager.DataManager;
import com.makersupv.makers.R;

import java.io.IOException;
import java.util.List;

public class UploadImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOOTER = 0;
    private static final int ITEM = 1;

    Context context;
    List<Uri> listOfPathImages;

    public class Item extends RecyclerView.ViewHolder {

        public ImageView projectUploadImageView;
        public ProgressBar progressBar;

        public Item(View view){
            super(view);
            projectUploadImageView = (ImageView) view.findViewById(R.id.projectUploadImageView);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBarUpload);
        }
    }

    class Footer extends RecyclerView.ViewHolder {
        ImageView addImageView;

        public Footer(View view){
            super(view);
            addImageView = (ImageView) view.findViewById(R.id.addImageView);
        }
    }

    public UploadImagesAdapter(Context context, List<Uri> listOfPathImages) {
        this.context = context;
        this.listOfPathImages = listOfPathImages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_add_photo, parent, false);
            return new Footer(view);
        }
        else if (viewType == ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_image_upload, parent, false);
            return new Item(view);
        }

        throw new RuntimeException("No recognized type");

    }

    @Override
    public int getItemViewType(int position){
        if(isFooter(position)) {
            return FOOTER;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        //Plus one because we always add 1 custom view at the end
        return listOfPathImages.size() + 1;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Footer) {
            Footer footer = (Footer) holder;
            Glide.with(context)
                    .load(R.drawable.ic_add_black)
                    .into(footer.addImageView);
        } else if (holder instanceof  Item) {
            Item item = (Item) holder;
            Glide.with(context)
                    .load(listOfPathImages.get(position))
                    .asBitmap()
                    .into(item.projectUploadImageView);
            try {
                DataManager.getInstance().uploadImage(context, item, listOfPathImages.get(position));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isFooter(int position){
        return position == getItemCount() - 1;
    }

    private Uri getItem(int position){
        return listOfPathImages.get(position);
    }

}
