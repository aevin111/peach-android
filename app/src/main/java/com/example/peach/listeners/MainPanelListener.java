package com.example.peach.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.R;
import com.example.peach.activities.CalendarActivity;
import com.example.peach.activities.CropsListActivity;
import com.example.peach.activities.DiseasesPlantsActivity;
import com.example.peach.activities.FieldCapacityActivity;
import com.example.peach.activities.PlantsActivity;
import com.example.peach.callbacks.CurrentCropCallback;
import com.example.peach.callbacks.SensorStatusCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.CurrentCropModel;
import com.example.peach.models.SensorStatusModel;
import com.example.peach.rest.Client;

public class MainPanelListener implements View.OnClickListener
{
    private Context context;
    private ImageView currentCropImageView;
    private String url;
    private TextView moistureLevelTextView;
    private TextView cropNameTextView;
    private TextView plantNameTextView;
    private TextView cropIdTextView;
    private TextView plantDateTextView;

    public MainPanelListener(Context context, ImageView currentCropImageView, String url, TextView moistureLevelTextView, TextView cropNameTextView, TextView plantNameTextView, TextView cropIdTextView, TextView plantDateTextView)
    {
        this.context = context;
        this.currentCropImageView = currentCropImageView;
        this.url = url;
        this.moistureLevelTextView = moistureLevelTextView;
        this.cropNameTextView = cropNameTextView;
        this.plantNameTextView = plantNameTextView;
        this.cropIdTextView = cropIdTextView;
        this.plantDateTextView = plantDateTextView;
    }

    private void startNextIntent(String choice)
    {
        Intent intent = null;

        if (choice.equals("PlantsActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting PlantsActivity");
            intent = new Intent(this.context, PlantsActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
        }

        else if (choice.equals("CropsListActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting CropsListActivity");
            intent = new Intent(this.context, CropsListActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
        }

        else if (choice.equals("CalendarActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting CalendarActivity");
            intent = new Intent(this.context, CalendarActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
        }

        else if (choice.equals("DiseasesPlantsActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting DiseasesPlantsActivity");
            intent = new Intent(this.context, DiseasesPlantsActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
        }

        else if (choice.equals("FieldCapacityActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting FieldCapacityActivity");
            intent = new Intent(this.context, FieldCapacityActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
        }
    }

    private void refreshSensorStatus()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data for refreshSensorStatus()");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<SensorStatusModel> call = api.getSensorStatus();
        call.enqueue(new SensorStatusCallback(this.context, this.moistureLevelTextView));
    }

    private void refreshCurrentCrop()
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data for getCurrentCrop()");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<CurrentCropModel> call = api.getCurrentCrop();
        call.enqueue(new CurrentCropCallback(this.context, this.currentCropImageView, this.cropNameTextView, this.plantNameTextView, this.cropIdTextView, this.plantDateTextView));
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.plantsButton)
        {
            this.startNextIntent("PlantsActivity");
        }

        else if (view.getId() == R.id.cropsButton)
        {
            this.startNextIntent("CropsListActivity");
        }

        else if (view.getId() == R.id.diseasesButton)
        {
            this.startNextIntent("DiseasesPlantsActivity");
        }

        else if (view.getId() == R.id.checklistButton)
        {
            this.startNextIntent("CalendarActivity");
        }

        else if (view.getId() == R.id.editFieldCapacityButton)
        {
            this.startNextIntent("FieldCapacityActivity");
        }

        else if (view.getId() == R.id.refreshButton)
        {
            this.refreshSensorStatus();
            this.refreshCurrentCrop();
        }
    }
}
