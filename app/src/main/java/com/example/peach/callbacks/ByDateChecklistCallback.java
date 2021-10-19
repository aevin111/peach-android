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
import com.example.peach.models.CurrentTaskModel;

public class ByDateChecklistCallback implements Callback<List<CurrentTaskModel>>
{
    private Context context;
    private ListView listView;

    public ByDateChecklistCallback(ListView listView, Context context)
    {
        this.context = context;
        this.listView = listView;
    }

    @Override
    public void onResponse(Call<List<CurrentTaskModel>> call, Response<List<CurrentTaskModel>> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            List<CurrentTaskModel> list = response.body();
            Log.i(this.getClass().getSimpleName(), "Returned with " + Integer.toString(list.size()) + " entries");
            ArrayAdapter<CurrentTaskModel> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }

        else
        {
            String message = response.message();
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<List<CurrentTaskModel>> call, Throwable t)
    {
        String message = t.getMessage();
        Log.i(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
