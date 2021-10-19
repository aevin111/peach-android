package com.example.peach.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.listeners.CreateNewCropListener;

public class CreateNewCropActivity extends AppCompatActivity
{
    private Button createNewCropBackButton;
    private Button createCropButton;
    private EditText cropNameEditText;
    private Integer plantId;
    private String url;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.url = intent.getStringExtra("url");
        this.plantId = intent.getIntExtra("plantId", 1);
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.createCropButton = findViewById(R.id.createCropButton);
        this.cropNameEditText = findViewById(R.id.cropNameEditText);
        this.createNewCropBackButton = findViewById(R.id.createNewCropBackButton);
        this.createNewCropBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CreateNewCropActivity.super.finish();
            }
        });
    }

    private void setListener()
    {
        Log.i(this.getClass().getSimpleName(), "Setting listener");
        CreateNewCropListener listener = new CreateNewCropListener(this, this.cropNameEditText, this.plantId, this.url);
        this.createCropButton.setOnClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_crop);
        this.getIntentData();
        this.loadUI();
        this.setListener();
    }
}
