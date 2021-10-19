package com.example.peach.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.listeners.PlantsListener;

public class PlantsActivity extends AppCompatActivity
{
    private Button plantsBackButton;
    private Button listAllButton;
    private Button searchButton;
    private String url;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.url = intent.getStringExtra("url");
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.listAllButton = findViewById(R.id.listAllButton);
        this.searchButton = findViewById(R.id.searchButton);
        this.plantsBackButton = findViewById(R.id.plantsBackButton);
        this.plantsBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PlantsActivity.super.finish();
            }
        });
    }

    private void setListener()
    {
        Log.i(this.getClass().getSimpleName(), "Setting listener");
        PlantsListener listener = new PlantsListener(this, this.url);
        this.listAllButton.setOnClickListener(listener);
        this.searchButton.setOnClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);
        this.getIntentData();
        this.loadUI();
        this.setListener();
    }
}
