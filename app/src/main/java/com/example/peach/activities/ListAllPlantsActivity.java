package com.example.peach.activities;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.R;
import com.example.peach.callbacks.ListAllPlantsCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.SummarizedPlantsListModel;
import com.example.peach.rest.Client;

public class ListAllPlantsActivity extends AppCompatActivity
{
    private Button listAllPlantsBackButton;
    private ListView listView;
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
        this.listView = findViewById(R.id.allPlantsListView);
        this.listView.setDivider(null);
        this.listView.setDividerHeight(0);
        this.listAllPlantsBackButton = findViewById(R.id.listAllPlantsBackButton);
        this.listAllPlantsBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ListAllPlantsActivity.super.finish();
            }
        });
    }

    private void fetch()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<List<SummarizedPlantsListModel>> call = api.getSummarizedPlantsList();
        call.enqueue(new ListAllPlantsCallback(this, this.listView, this.url));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_plants);
        this.getIntentData();
        this.loadUI();
        this.fetch();
    }
}
