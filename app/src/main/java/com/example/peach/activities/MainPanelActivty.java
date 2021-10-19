package com.example.peach.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.R;
import com.example.peach.callbacks.CurrentCropCallback;
import com.example.peach.callbacks.SensorStatusCallback;
import com.example.peach.interfaces.API;
import com.example.peach.listeners.DeleteCurrentCropListener;
import com.example.peach.listeners.MainPanelListener;
import com.example.peach.models.CurrentCropModel;
import com.example.peach.models.SensorStatusModel;
import com.example.peach.rest.Client;

public class MainPanelActivty extends AppCompatActivity
{
    private Button editFieldCapacityButton;
    private Button checkListButton;
    private Button cropsButton;
    private Button diseasesButton;
    private Button plantsButton;
    private Button refreshButton;
    private Button deleteCurrentCropButton;
    private ImageView currentCropImageView;
    private String url;
    private TextView moistureLevelTextView;
    private TextView cropNameTextView;
    private TextView plantNameTextView;
    private TextView cropIdTextView;
    private TextView plantDateTextView;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.url = intent.getStringExtra("url");
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.cropNameTextView = findViewById(R.id.cropNameTextView);
        this.plantNameTextView = findViewById(R.id.plantNameTextView);
        this.cropIdTextView = findViewById(R.id.cropIdTextView);
        this.plantDateTextView = findViewById(R.id.plantDateTextView);
        this.moistureLevelTextView = findViewById(R.id.moistureLevelTextView);
        this.checkListButton = findViewById(R.id.checklistButton);
        this.cropsButton = findViewById(R.id.cropsButton);
        this.refreshButton = findViewById(R.id.refreshButton);
        this.editFieldCapacityButton = findViewById(R.id.editFieldCapacityButton);
        this.diseasesButton = findViewById(R.id.diseasesButton);
        this.plantsButton = findViewById(R.id.plantsButton);
        this.deleteCurrentCropButton = findViewById(R.id.deleteCurrentCropButton);
        this.currentCropImageView = findViewById(R.id.currentCropImageView);
        this.currentCropImageView.setImageResource(android.R.color.transparent);
    }

    private void setListener()
    {
        Log.i(this.getClass().getSimpleName(), "Setting listener");
        MainPanelListener listener = new MainPanelListener(this, this.currentCropImageView, this.url, this.moistureLevelTextView, this.cropNameTextView, this.plantNameTextView, this.cropIdTextView, this.plantDateTextView);
        this.checkListButton.setOnClickListener(listener);
        this.cropsButton.setOnClickListener(listener);
        this.refreshButton.setOnClickListener(listener);
        this.diseasesButton.setOnClickListener(listener);
        this.plantsButton.setOnClickListener(listener);
        this.deleteCurrentCropButton.setOnClickListener(new DeleteCurrentCropListener(this, this.url));
        this.editFieldCapacityButton.setOnClickListener(listener);
    }

    private void getCurrentCrop()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data for getCurrentCrop()");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<CurrentCropModel> call = api.getCurrentCrop();
        call.enqueue(new CurrentCropCallback(this, this.currentCropImageView, this.cropNameTextView, this.plantNameTextView, this.cropIdTextView, this.plantDateTextView));
    }

    public void getCurrentSensorStatus()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data for getCurrentSensorStatus()");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<SensorStatusModel> call = api.getSensorStatus();
        call.enqueue(new SensorStatusCallback(this, this.moistureLevelTextView));
    }

    private void fetch()
    {
        this.getCurrentCrop();
        this.getCurrentSensorStatus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel_activty);
        this.getIntentData();
        this.loadUI();
        this.setListener();
        this.fetch();
    }
}
