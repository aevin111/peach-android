package com.example.peach.listeners;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.peach.activities.Symptom2Activity;
import com.example.peach.models.SummarizedPlantsListModel;

public class DiseasesPlantsListener implements AdapterView.OnItemClickListener
{
    private Context context;
    private List<SummarizedPlantsListModel> list;
    private String url;

    public DiseasesPlantsListener(Context context, List<SummarizedPlantsListModel> list, String url)
    {
        this.context = context;
        this.list = list;
        this.url = url;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.i(this.getClass().getSimpleName(), "Selected plant id " + Integer.toString(list.get(i).getPlantId()));
        Intent intent = new Intent(this.context, Symptom2Activity.class);
        intent.putExtra("url", this.url);
        intent.putExtra("plantId", list.get(i).getPlantId());
        this.context.startActivity(intent);
    }
}
