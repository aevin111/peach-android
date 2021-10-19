package com.example.peach.callbacks;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.models.CreateNewCropModel;

public class CreateNewCropCallback implements Callback<CreateNewCropModel>
{
    private Context context;

    public CreateNewCropCallback(Context context)
    {
        this.context = context;
    }

    @Override
    public void onResponse(Call<CreateNewCropModel> call, Response<CreateNewCropModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            CreateNewCropModel model = response.body();
            Log.i(this.getClass().getSimpleName(), model.getMessage());
            Toast.makeText(this.context, model.getMessage(), Toast.LENGTH_LONG).show();
            ((Activity) this.context).finish();
        }

        else
        {
            String message = response.message();
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<CreateNewCropModel> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
