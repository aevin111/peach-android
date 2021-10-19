package com.example.peach.callbacks;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.listeners.Symptom2Listener;
import com.example.peach.models.SymptomModel;

public class Symptom2Callback implements Callback<List<SymptomModel>>
{
    private Button analyzeButton;
    private Context context;
    private Integer plantId;
    private ListView listView;
    private String url;

    public Symptom2Callback(Button analyzeButton, Context context, Integer plantId, ListView listView, String url)
    {
        this.analyzeButton = analyzeButton;
        this.context = context;
        this.plantId = plantId;
        this.listView = listView;
        this.url = url;
    }

    @Override
    public void onResponse(Call<List<SymptomModel>> call, Response<List<SymptomModel>> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            List<SymptomModel> list = response.body();
            Log.i(this.getClass().getSimpleName(), "Returned with " + Integer.toString(list.size()) + " entries");
            ArrayAdapter<SymptomModel> adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, list);
            this.listView.setAdapter(adapter);
            Symptom2Listener listener = new Symptom2Listener(this.context, this.plantId, list, this.listView, this.url);
            this.listView.setOnItemClickListener(listener);
            this.analyzeButton.setOnClickListener(listener);
        }

        else
        {
            String message = response.message();
            Log.w(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<List<SymptomModel>> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
