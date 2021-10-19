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
import com.example.peach.callbacks.Symptom2Callback;
import com.example.peach.interfaces.API;
import com.example.peach.models.SymptomModel;
import com.example.peach.rest.Client;

public class Symptom2Activity extends AppCompatActivity
{
    private Button symptom2BackButton;
    private Button analyzeButton;
    private Integer plantId;
    private ListView listView;
    private String url;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.plantId = Integer.valueOf(intent.getIntExtra("plantId", 1));
        this.url = intent.getStringExtra("url");
    }

    private void loadUI()
    {
        this.listView = findViewById(R.id.symptoms2ListView);
        this.analyzeButton = findViewById(R.id.analyzeButton);
        this.symptom2BackButton = findViewById(R.id.symptom2BackButton);
        this.symptom2BackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Symptom2Activity.super.finish();
            }
        });
    }

    private void fetch()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<List<SymptomModel>> call = api.getSymptoms(this.plantId);
        call.enqueue(new Symptom2Callback(this.analyzeButton, this, this.plantId, this.listView, this.url));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom2);
        this.getIntentData();
        this.loadUI();
        this.fetch();
    }
}
