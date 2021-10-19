package com.example.peach.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.callbacks.FieldCapacityCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.FieldCapacityModel;
import com.example.peach.rest.Client;

public class FieldCapacityListener implements View.OnClickListener
{
    private Context context;
    private EditText fieldCapacityEditText;
    private String url;

    public FieldCapacityListener(Context context, EditText fieldCapacityEditText, String url)
    {
        this.context = context;
        this.fieldCapacityEditText = fieldCapacityEditText;
        this.url = url;
    }

    @Override
    public void onClick(View view)
    {
        Log.i(this.getClass().getSimpleName(), "Fetching callback data");
        Client client = new Client(url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<FieldCapacityModel> call = api.setFieldCapacity(Integer.parseInt(this.fieldCapacityEditText.getText().toString()));
        call.enqueue(new FieldCapacityCallback(this.context));
    }
}
