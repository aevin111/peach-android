package com.example.peach.callbacks;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.activities.CropsListActivity;
import com.example.peach.errors.ServerError;
import com.example.peach.listeners.DeleteCropListener;
import com.example.peach.models.DeleteCropModel;

public class DeleteCropCallback implements Callback<DeleteCropModel>
{
    private Context context;

    public DeleteCropCallback(Context context)
    {
        this.context = context;
    }

    private void alert(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Notification");
        builder.setMessage(message);
        AlertDialog alertDialog = builder.create();
        builder.setPositiveButton("OK", new DeleteCropListener(alertDialog));
        builder.show();
    }

    @Override
    public void onResponse(Call<DeleteCropModel> call, Response<DeleteCropModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            DeleteCropModel model = response.body();
            String message = model.getMessage();
            this.alert(message);
            ((CropsListActivity) this.context).finish();
        }

        else
        {
            String message = response.message();
            this.alert("This is the crop that is currently set! Delete the current crop first");
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<DeleteCropModel> call, Throwable t)
    {
        String message = t.getMessage();
        Log.e(this.getClass().getSimpleName(), message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
