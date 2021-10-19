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
import com.example.peach.callbacks.TodayChecklistCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.CurrentTaskModel;
import com.example.peach.rest.Client;

public class TodayChecklistActivity extends AppCompatActivity
{
    private Button todayChecklistBackButton;
    private ListView listView;
    private String date;
    private String url;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.date = intent.getStringExtra("date");
        this.url = intent.getStringExtra("url");
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.listView = findViewById(R.id.checklistListView);
        this.todayChecklistBackButton = findViewById(R.id.todayChecklistBackButton);
        this.todayChecklistBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TodayChecklistActivity.super.finish();
            }
        });
    }

    private void fetch()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<List<CurrentTaskModel>> call = api.getCurrentTasks("today");
        call.enqueue(new TodayChecklistCallback(this, this.listView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_check_list);
        this.getIntentData();
        this.loadUI();
        this.fetch();
    }
}
