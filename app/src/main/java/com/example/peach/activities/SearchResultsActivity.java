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
import com.example.peach.callbacks.SearchResultsCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.PlantsSearchResultModel;
import com.example.peach.rest.Client;

public class SearchResultsActivity extends AppCompatActivity
{
    private Button searchResultsBackButton;
    private ListView listView;
    private String searchString;
    private String url;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.url = intent.getStringExtra("url");
        this.searchString = intent.getStringExtra("searchString");
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.listView = findViewById(R.id.searchResultsListView);
        this.searchResultsBackButton = findViewById(R.id.searchResultsBackButton);
        this.searchResultsBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SearchResultsActivity.super.finish();
            }
        });
    }

    private void fetch()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<List<PlantsSearchResultModel>> call = api.searchForPlant(this.searchString);
        call.enqueue(new SearchResultsCallback(this, this.listView, this.url));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        this.getIntentData();
        this.loadUI();
        this.fetch();
    }
}
