package com.example.peach.listeners;

import android.content.Context;
import android.view.View;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.callbacks.DeleteCurrentCropCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.DeleteCurrentCropModel;
import com.example.peach.rest.Client;

public class DeleteCurrentCropListener implements View.OnClickListener
{
    private Context context;
    private String url;

    public DeleteCurrentCropListener(Context context, String url)
    {
        this.context = context;
        this.url = url;
    }

    @Override
    public void onClick(View view)
    {
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<DeleteCurrentCropModel> call = api.deleteCurrentCrop();
        call.enqueue(new DeleteCurrentCropCallback(this.context));
    }
}
