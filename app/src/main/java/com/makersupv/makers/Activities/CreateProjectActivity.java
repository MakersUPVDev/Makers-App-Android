/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.makersupv.makers.Adapters.SkillsAdapter;
import com.makersupv.makers.Adapters.UploadImagesAdapter;
import com.makersupv.makers.DataManager.DataManager;
import com.makersupv.makers.Models.Image;
import com.makersupv.makers.Models.Project;
import com.makersupv.makers.Models.Skill;
import com.makersupv.makers.R;
import com.makersupv.makers.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CreateProjectActivity extends AppCompatActivity {

    private int IMAGE_CODE_REQUEST = 101;

    //UploaderRecycler
    private RecyclerView uploadRecyclerView;
    private UploadImagesAdapter uploadImagesAdapter;
    private RecyclerView.LayoutManager uploadImagesLayout;

    //SkillsRecycler
    private RecyclerView skillsReciclerView;
    private SkillsAdapter skillsAdapter;
    private RecyclerView.LayoutManager skillsLayout;

    private FloatingActionButton fab;
    private EditText nameEditText;
    private EditText descriptionEditText;

    private List<Uri> listOfUri;
    private List<Image> listOfImages;
    private List<Skill> listOfSkill;
    private List<Skill> listOfSelectedSkills;

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        context = getApplicationContext();

        fab = (FloatingActionButton) findViewById(R.id.fab);

        uploadRecyclerView = (RecyclerView) findViewById(R.id.recyclerUploadImageView);

        nameEditText = (EditText) findViewById(R.id.titleEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);

        listOfUri = new ArrayList<>();
        listOfImages = new ArrayList<>();

        uploadImagesAdapter = new UploadImagesAdapter(this, listOfUri, listOfImages);

        uploadImagesLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        uploadRecyclerView.setAdapter(uploadImagesAdapter);
        uploadRecyclerView.setLayoutManager(uploadImagesLayout);

        //Populate Skills RecyclerView
        DataManager.getInstance().getAllSkills(new DataManager.SkillsCallback() {
            @Override
            public void doneSkills(final List<Skill> skills) {
                Handler mainHandler = new Handler(context.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listOfSkill = new ArrayList<>();
                        listOfSelectedSkills = new ArrayList<>();
                        listOfSkill.addAll(skills);
                        skillsReciclerView = (RecyclerView) findViewById(R.id.skillsRecyclerView);
                        skillsAdapter = new SkillsAdapter(context, skills);
                        skillsLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                        skillsReciclerView.setAdapter(skillsAdapter);
                        skillsReciclerView.setLayoutManager(skillsLayout);

                        skillsReciclerView.addOnItemTouchListener(
                                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener(){
                                    @Override
                                    public void onItemClick(View view, final int position) {
                                        SkillsAdapter.ViewHolder holder = (SkillsAdapter.ViewHolder) skillsReciclerView.findViewHolderForAdapterPosition(position);
                                        if(holder.addedImage.getVisibility() == View.GONE){
                                            listOfSelectedSkills.add(listOfSkill.get(position));
                                            holder.addedImage.setVisibility(View.VISIBLE);
                                        } else {
                                            listOfSelectedSkills.remove(listOfSkill.get(position));
                                            holder.addedImage.setVisibility(View.GONE);
                                        }
                                    }
                                })
                        );
                    }
                });

            }
        });

        //Click listeners
        uploadRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        if (position == listOfUri.size()) {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.images)), IMAGE_CODE_REQUEST);
                        } else{
                            showAlertDialog(position);
                        }
                    }
                })
        );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Project project = new Project();
                project.setProjectName(nameEditText.getText().toString());
                project.setDescription(descriptionEditText.getText().toString());
                project.setImages(listOfImages);
                project.setSkills(listOfSelectedSkills);
                project.saveInBackground();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            listOfUri.add(uri);

            uploadImagesAdapter.notifyDataSetChanged();
        }
    }

    private void showAlertDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.delete_image));
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listOfUri.remove(position);
                listOfImages.remove(position);
                uploadImagesAdapter.notifyItemRemoved(position);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Pass
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
