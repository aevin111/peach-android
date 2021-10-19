package com.example.peach.callbacks;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.activities.MainPanelActivty;
import com.example.peach.errors.ServerError;
import com.example.peach.models.APIStatusModel;

public class MainCallback implements Callback<APIStatusModel>
{
    private Context context;
    private String url;

    public MainCallback(Context context, String url)
    {
        this.context = context;
        this.url = url;
    }

    @Override
    public void onResponse(Call<APIStatusModel> call, Response<APIStatusModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            APIStatusModel apiStatusModel = response.body();
            int statusCode = apiStatusModel.getResponse();

            if (statusCode == 200)
            {
                Log.i(this.getClass().getSimpleName(), "Logged in with status code " + Integer.toString(response.code()));
                Intent intent = new Intent(this.context, MainPanelActivty.class);
                intent.putExtra("url", this.url);
                this.context.startActivity(intent);
            }

            else
            {
                String message = "Failed to login! \nCode: " + statusCode + "\nURL: " + this.url;
                Log.e(this.getClass().getSimpleName(), message);
                Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
            }
        }

        else
        {
            String message = response.message();
            Log.w(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<APIStatusModel> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
