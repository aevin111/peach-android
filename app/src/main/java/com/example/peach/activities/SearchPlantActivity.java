package com.example.peach.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.listeners.SearchPlantListener;

public class SearchPlantActivity extends AppCompatActivity
{
    private Button searchPlantBackButton;
    private Button searchButton;
    private EditText searchStringEditText;
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
        this.searchButton = (Button) findViewById(R.id.searchButton);
        this.searchStringEditText = (EditText) findViewById(R.id.searchStringEditText);
        this.searchPlantBackButton = findViewById(R.id.searchPlantBackButton);
        this.searchPlantBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SearchPlantActivity.super.finish();
            }
        });
    }

    private void setListener()
    {
        Log.i(this.getClass().getSimpleName(), "Setting listener");
        SearchPlantListener listener = new SearchPlantListener(this, this.searchStringEditText, this.url);
        this.searchButton.setOnClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plant);
        this.getIntentData();
        this.loadUI();
        this.setListener();
    }
}
