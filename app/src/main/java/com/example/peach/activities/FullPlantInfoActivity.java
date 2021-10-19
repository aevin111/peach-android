package com.example.peach.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.models.PlantsSearchResultModel;

public class FullPlantInfoActivity extends AppCompatActivity
{
    private Button fullPlantInfoBackButton;
    private PlantsSearchResultModel model;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.model = (PlantsSearchResultModel) intent.getSerializableExtra("result");
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        ListView listView = findViewById(R.id.infoListView);
        List<String> listString = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listString);
        listView.setAdapter(adapter);
        adapter.add(this.model.getInfo());
        this.fullPlantInfoBackButton = findViewById(R.id.fullPlantInfoBackButton);
        this.fullPlantInfoBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FullPlantInfoActivity.super.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_plant_info);
        this.getIntentData();
        this.loadUI();
    }
}
