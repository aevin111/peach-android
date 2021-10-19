package com.example.peach.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.R;
import com.example.peach.errors.ServerError;
import com.example.peach.models.CurrentCropModel;

public class CurrentCropCallback implements Callback<CurrentCropModel>
{
    private Context context;
    private ImageView currentCropImageView;
    private TextView cropNameTextView;
    private TextView plantNameTextView;
    private TextView cropIdTextView;
    private TextView plantDateTextView;

    public CurrentCropCallback(Context context, ImageView currentCropImageView, TextView cropNameTextView, TextView plantNameTextView, TextView cropIdTextView, TextView plantDateTextView)
    {
        this.context = context;
        this.currentCropImageView = currentCropImageView;
        this.cropNameTextView = cropNameTextView;
        this.cropIdTextView = cropIdTextView;
        this.plantNameTextView = plantNameTextView;
        this.plantDateTextView = plantDateTextView;
    }

    @Override
    public void onResponse(Call<CurrentCropModel> call, Response<CurrentCropModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            CurrentCropModel model = response.body();
            int cropId = model.getPlantId();
            this.cropIdTextView.setText(Integer.toString(cropId));
            this.cropNameTextView.setText(model.getCropName());
            this.plantNameTextView.setText(model.getName());
            this.plantDateTextView.setText(model.getPlantDate());

            switch (cropId)
            {
                case 1:
                    this.currentCropImageView.setBackgroundResource(R.drawable.p1);
                    break;

                case 2:
                    this.currentCropImageView.setBackgroundResource(R.drawable.p2);
                    break;

                case 3:
                    this.currentCropImageView.setBackgroundResource(R.drawable.p3);
                    break;

                case 4:
                    this.currentCropImageView.setBackgroundResource(R.drawable.p4);
                    break;

                default:
                    this.currentCropImageView.setBackgroundResource(R.drawable.unavailable);
                    break;
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
    public void onFailure(Call<CurrentCropModel> call, Throwable t)
    {
        String message = t.getMessage();
        Log.w(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
