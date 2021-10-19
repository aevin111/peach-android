package com.example.peach.activities;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.R;
import com.example.peach.callbacks.SymptomCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.SymptomModel;
import com.example.peach.rest.Client;

public class SymptomActivity extends AppCompatActivity
{
    private int plantId;
    private ListView listView;
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
        this.listView = findViewById(R.id.symptomListView);
    }

    private void fetch()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<List<SymptomModel>> call = api.getSymptoms(this.plantId);
        call.enqueue(new SymptomCallback(this, this.listView, this.url));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        this.getIntentData();
        this.loadUI();
        this.fetch();
    }
}
