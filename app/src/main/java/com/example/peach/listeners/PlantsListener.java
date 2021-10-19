package com.example.peach.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.peach.R;
import com.example.peach.activities.ListAllPlantsActivity;
import com.example.peach.activities.SearchPlantActivity;

public class PlantsListener implements View.OnClickListener
{
    private Context context;
    private String url;

    public PlantsListener(Context context, String url)
    {
        this.context = context;
        this.url = url;
    }

    private void startNextIntent(String choice)
    {
        Intent intent = null;

        if (choice.equals("ListAllPlantsActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting ListAllPlantsActivity");
            intent = new Intent(this.context, ListAllPlantsActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
            ((Activity) this.context).finish();
        }

        else if (choice.equals("SearchPlantActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting SearchPlantActivity");
            intent = new Intent(this.context, SearchPlantActivity.class);
            intent.putExtra("url", this.url);
            this.context.startActivity(intent);
            ((Activity) this.context).finish();
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.listAllButton)
        {
            this.startNextIntent("ListAllPlantsActivity");
        }

        else if (view.getId() == R.id.searchButton)
        {
            this.startNextIntent("SearchPlantActivity");
        }
    }
}
