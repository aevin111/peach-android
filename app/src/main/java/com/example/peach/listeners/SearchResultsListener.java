package com.example.peach.listeners;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.peach.activities.FullPlantInfoActivity;
import com.example.peach.models.PlantsSearchResultModel;

public class SearchResultsListener implements AdapterView.OnItemClickListener
{
    private Context context;
    private List<PlantsSearchResultModel> list;
    private String url;

    public SearchResultsListener(Context context, List<PlantsSearchResultModel> list, String url)
    {
        this.context = context;
        this.list = list;
        this.url = url;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.i(this.getClass().getSimpleName(), " Starting FullPlantInfoActivity");
        PlantsSearchResultModel result = this.list.get(i);
        Intent intent = new Intent(this.context, FullPlantInfoActivity.class);
        intent.putExtra("result", result);
        this.context.startActivity(intent);
    }
}
