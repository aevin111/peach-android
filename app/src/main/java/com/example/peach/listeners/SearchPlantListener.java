package com.example.peach.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.peach.activities.SearchResultsActivity;

public class SearchPlantListener implements View.OnClickListener
{
    private Context context;
    private EditText searchStringEditText;
    private String url;

    public SearchPlantListener(Context context, EditText searchStringEditText, String url)
    {
        this.context = context;
        this.searchStringEditText = searchStringEditText;
        this.url = url;
    }

    @Override
    public void onClick(View view)
    {
        Log.i(this.getClass().getSimpleName(), " Starting SearchResultsActivity");
        Intent intent = new Intent(this.context, SearchResultsActivity.class);
        intent.putExtra("url", this.url);
        intent.putExtra("searchString", this.searchStringEditText.getText().toString());
        this.context.startActivity(intent);
    }
}
