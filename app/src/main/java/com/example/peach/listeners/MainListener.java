package com.example.peach.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.callbacks.MainCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.APIStatusModel;
import com.example.peach.rest.Client;

public class MainListener implements View.OnClickListener
{
    private Context context;
    private EditText ipEditText;
    private EditText passwordEditText;

    public MainListener(Context context, EditText ipEditText, EditText passwordEditText)
    {
        this.context = context;
        this.ipEditText = ipEditText;
        this.passwordEditText = passwordEditText;
    }

    @Override
    public void onClick(View view)
    {
        try
        {
            String url = "http://" + this.ipEditText.getText().toString() + "/";
            Log.i(this.getClass().getSimpleName(), "Logging in to service @ " + url);
            Client client = new Client(url);
            Retrofit retrofit = client.getRetrofitClient();
            API api = retrofit.create(API.class);
            Call<APIStatusModel> call = api.getStatus(passwordEditText.getText().toString());
            call.enqueue(new MainCallback(this.context, url));
        }

        catch (IllegalArgumentException exception)
        {
            String message = exception.getMessage();
            Log.e(this.getClass().getName(), message);
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
        }
    }
}
