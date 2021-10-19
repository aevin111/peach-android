package com.example.peach.callbacks;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.R;
import com.example.peach.adapters.SymptomAdapter;
import com.example.peach.errors.ServerError;
import com.example.peach.models.SymptomModel;

public class SymptomCallback implements Callback<List<SymptomModel>>
{
    private Context context;
    private ListView listView;
    private String url;

    public SymptomCallback(Context context, ListView listView, String url)
    {
        this.context = context;
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
            SymptomAdapter adapter = new SymptomAdapter(this.context, R.layout.item_row, list, this.url, this.listView);
            this.listView.setAdapter(adapter);
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
