package com.makersupv.makers;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

public class ProjectDetailsActivity extends AppCompatActivity {

    private String projectId;
    private ArrayList<Image> parseImages;
    private ArrayList<Skill> parseSkills;

    private ParseImageView projectImageView;
    private TextView tv_description;
    private ParseImageView skill1, skill2, skill3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        projectImageView = (ParseImageView) findViewById(R.id.projectImageView);

        tv_description = (TextView) findViewById(R.id.description);

        skill1 = (ParseImageView) findViewById(R.id.skill1);

        skill2 = (ParseImageView) findViewById(R.id.skill2);

        skill3 = (ParseImageView) findViewById(R.id.skill3);

        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("projectId");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Project");
        query.include("skills");
        query.include("images");
        query.getInBackground(projectId, new GetCallback<ParseObject>() {
            public void done(ParseObject project, ParseException e) {
                if (e == null) {
                    collapsingToolbarLayout.setTitle(project.getString("name"));
                    tv_description.setText(project.getString("description"));
                    parseImages = (ArrayList<Image>) project.get("images");
                    parseSkills = (ArrayList<Skill>) project.get("skills");
                    fetchImages();
                    fetchSkills();
                } else {
                    // something went wrong
                }
            }
        });
    }

    public void fetchImages(){
        if(parseImages != null && parseImages.size() != 0){
            ((ParseFile) parseImages.get(0).get("image")).cancel();
            projectImageView.setParseFile(((ParseFile) parseImages.get(0).get("image")));
            projectImageView.loadInBackground();
        }
        else{
            projectImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.makers_logo));
        }
    }

    public void fetchSkills(){
        if(parseSkills != null && parseSkills.size() != 0){

            if (parseSkills.size() > 0){
                ((ParseFile) parseSkills.get(0).get("icon")).cancel();
                skill1.setParseFile((ParseFile) parseSkills.get(0).get("icon"));
                skill1.loadInBackground();
            }
            else {
                skill1.setImageResource(android.R.color.transparent);
            }

            if(parseSkills.size() > 1) {
                ((ParseFile) parseSkills.get(1).get("icon")).cancel();
                skill2.setParseFile((ParseFile) parseSkills.get(1).get("icon"));
                skill2.loadInBackground();

            }
            else {
                skill2.setImageResource(android.R.color.transparent);
            }

            if (parseSkills.size()  > 2) {
                ((ParseFile) parseSkills.get(2).get("icon")).cancel();
                skill3.setParseFile((ParseFile) parseSkills.get(2).get("icon"));
                skill3.loadInBackground();
            }
            else {
                skill3.setImageResource(android.R.color.transparent);
            }
        }
        else {
            skill1.setImageResource(android.R.color.transparent);
            skill2.setImageResource(android.R.color.transparent);
            skill3.setImageResource(android.R.color.transparent);
        }
    }
}