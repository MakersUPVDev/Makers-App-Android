/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 */

package com.makersupv.makers.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.makersupv.makers.Adapters.UploadImagesAdapter;
import com.makersupv.makers.R;
import com.makersupv.makers.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CreateProjectActivity extends AppCompatActivity {

    private int IMAGE_CODE_REQUEST = 101;

    private RecyclerView uploadRecyclerView;
    private UploadImagesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Uri> listOfUri;

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        context = getApplicationContext();

        uploadRecyclerView = (RecyclerView) findViewById(R.id.recyclerUploadImageView);

        listOfUri = new ArrayList<>();

        adapter = new UploadImagesAdapter(this, listOfUri);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        uploadRecyclerView.setAdapter(adapter);
        uploadRecyclerView.setLayoutManager(layoutManager);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            listOfUri.add(uri);

            adapter.notifyDataSetChanged();
        }
    }

    private void showAlertDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.delete_image));
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listOfUri.remove(position);
                adapter.notifyItemRemoved(position);
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
