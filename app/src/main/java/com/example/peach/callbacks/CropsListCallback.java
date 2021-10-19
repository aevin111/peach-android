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
import com.example.peach.listeners.SetCurrentCropListener;
import com.example.peach.models.CropsListModel;

public class CropsListCallback implements Callback<List<CropsListModel>>
{
    private Context context;
    private ListView listView;
    private String url;

    public CropsListCallback(Context context, ListView listView, String url)
    {
        this.context = context;
        this.listView = listView;
        this.url = url;
    }

    @Override
    public void onResponse(Call<List<CropsListModel>> call, Response<List<CropsListModel>> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            List<CropsListModel> list = response.body();
            Log.i(this.getClass().getSimpleName(), "Returned with " + Integer.toString(list.size()) + " entries");
            ArrayAdapter<CropsListModel> adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, list);
            this.listView.setAdapter(adapter);
            this.listView.setOnItemClickListener(new SetCurrentCropListener(this.context, list, this.url));
        }

        else
        {
            String message = response.message();
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<List<CropsListModel>> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
