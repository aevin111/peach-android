package com.example.peach.listeners;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.peach.activities.CreateNewCropActivity;
import com.example.peach.models.SummarizedPlantsListModel;

public class ListAllPlantsListener implements AdapterView.OnItemClickListener
{
    private Context context;
    private List<SummarizedPlantsListModel> model;
    private String url;

    public ListAllPlantsListener(Context context, List<SummarizedPlantsListModel> model, String url)
    {
        this.context = context;
        this.model = model;
        this.url = url;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.i(this.getClass().getSimpleName(), " Starting CreateNewCropActivity");
        Intent intent = new Intent(this.context, CreateNewCropActivity.class);
        intent.putExtra("url", this.url);
        intent.putExtra("plantId", this.model.get(i).getPlantId());
        this.context.startActivity(intent);
        ((Activity) this.context).finish();
    }
}
