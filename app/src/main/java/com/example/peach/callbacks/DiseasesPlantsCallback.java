package com.example.peach.callbacks;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.listeners.DiseasesPlantsListener;
import com.example.peach.models.SummarizedPlantsListModel;

public class DiseasesPlantsCallback implements Callback<List<SummarizedPlantsListModel>>
{
    private Context context;
    private ListView listView;
    private String url;

    public DiseasesPlantsCallback(Context context, ListView listView, String url)
    {
        this.context = context;
        this.listView = listView;
        this.url = url;
    }

    private void setListener(List<SummarizedPlantsListModel> list)
    {
        this.listView.setOnItemClickListener(new DiseasesPlantsListener(this.context, list, this.url));
    }

    @Override
    public void onResponse(Call<List<SummarizedPlantsListModel>> call, Response<List<SummarizedPlantsListModel>> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            List<SummarizedPlantsListModel> list = response.body();
            Log.i(this.getClass().getSimpleName(), "Returned with " + Integer.toString(list.size()) + " entries");
            ArrayAdapter<SummarizedPlantsListModel> adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, list);
            this.listView.setAdapter(adapter);
            this.setListener(list);
        }

        else
        {
            String message = response.message();
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<List<SummarizedPlantsListModel>> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
