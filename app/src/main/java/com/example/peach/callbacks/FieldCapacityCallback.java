package com.example.peach.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.models.FieldCapacityModel;

public class FieldCapacityCallback implements Callback<FieldCapacityModel>
{
    private Context context;

    public FieldCapacityCallback(Context context)
    {
        this.context = context;
    }

    @Override
    public void onResponse(Call<FieldCapacityModel> call, Response<FieldCapacityModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            FieldCapacityModel model = response.body();
            String message = model.getMessage();
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }

        else
        {
            String message = response.message();
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<FieldCapacityModel> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
