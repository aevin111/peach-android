package com.example.peach.callbacks;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.peach.errors.ServerError;
import com.example.peach.listeners.ClassificationsListener;
import com.example.peach.models.ClassificationsModel;

public class ClassificationsCallback implements Callback<ClassificationsModel>
{
    private Context context;

    public ClassificationsCallback(Context context)
    {
        this.context = context;
    }

    private void alert(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Possible Diseases");
        builder.setMessage(message);
        AlertDialog alertDialog = builder.create();
        builder.setPositiveButton("OK", new ClassificationsListener(alertDialog));
        builder.show();
    }

    @Override
    public void onResponse(Call<ClassificationsModel> call, Response<ClassificationsModel> response)
    {
        if (response.body() != null || ServerError.success(response.code()))
        {
            ClassificationsModel classifications = response.body();
            StringBuilder builder = new StringBuilder();
            builder.append("Possible diseases:");
            builder.append("\n");

            for (String classification : classifications.getClassifications())
            {
                builder.append(classification);
                builder.append("\n");
            }

            String verdict = builder.toString();
            this.alert(verdict);
        }

        else
        {
            String message = response.message();
            Log.e(this.getClass().getSimpleName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<ClassificationsModel> call, Throwable t)
    {

    }
}
