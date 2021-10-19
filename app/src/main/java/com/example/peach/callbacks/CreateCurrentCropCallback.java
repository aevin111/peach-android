package com.example.peach.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.models.CreateCurrentCropModel;

public class CreateCurrentCropCallback implements Callback<CreateCurrentCropModel>
{
    private Context context;

    public CreateCurrentCropCallback(Context context)
    {
        this.context = context;
    }

    @Override
    public void onResponse(Call<CreateCurrentCropModel> call, Response<CreateCurrentCropModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            CreateCurrentCropModel model = response.body();
            String message = model.getMessage();
            Log.w(this.getClass().getSimpleName(), message);
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
    public void onFailure(Call<CreateCurrentCropModel> call, Throwable t)
    {

    }
}
