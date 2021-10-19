package com.example.peach.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.models.SensorStatusModel;

public class SensorStatusCallback implements Callback<SensorStatusModel>
{
    private Context context;
    private TextView moistureLevelTextView;

    public SensorStatusCallback(Context context, TextView moistureLevelTextView)
    {
        this.context = context;
        this.moistureLevelTextView = moistureLevelTextView;
    }

    @Override
    public void onResponse(Call<SensorStatusModel> call, Response<SensorStatusModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            SensorStatusModel model = response.body();
            this.moistureLevelTextView.setText(model.getMoistureLevel());
        }

        else
        {
            String message = response.message();
            Log.w(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<SensorStatusModel> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
