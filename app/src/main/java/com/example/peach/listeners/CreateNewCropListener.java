package com.example.peach.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.callbacks.CreateNewCropCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.CreateNewCropModel;
import com.example.peach.rest.Client;

public class CreateNewCropListener implements View.OnClickListener
{
    private Context context;
    private EditText cropNameEditText;
    private Integer plantId;
    private String url;

    public CreateNewCropListener(Context context, EditText cropNameEditText, Integer plantId, String url)
    {
        this.context = context;
        this.cropNameEditText = cropNameEditText;
        this.plantId = plantId;
        this.url = url;
    }

    @Override
    public void onClick(View view)
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<CreateNewCropModel> call = api.createCrop(this.plantId, this.cropNameEditText.getText().toString());
        call.enqueue(new CreateNewCropCallback(this.context));
    }
}
