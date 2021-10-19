package com.example.peach.listeners;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import retrofit2.Call;
import retrofit2.Retrofit;

import com.example.peach.callbacks.CreateCurrentCropCallback;
import com.example.peach.callbacks.DeleteCropCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.CreateCurrentCropModel;
import com.example.peach.models.CropsListModel;
import com.example.peach.models.DeleteCropModel;
import com.example.peach.rest.Client;

public class CropChoiceListener implements DialogInterface.OnClickListener
{
    private Context context;
    private AlertDialog alertDialog;
    private List<CropsListModel> list;
    private int index;
    private String url;

    public CropChoiceListener(Context context, AlertDialog alertDialog, List<CropsListModel> list, int index, String url)
    {
        this.context = context;
        this.alertDialog = alertDialog;
        this.list = list;
        this.index = index;
        this.url = url;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i)
    {
        if (i == DialogInterface.BUTTON_NEGATIVE)
        {
            Integer cropId = this.list.get(this.index).getCropId();
            Client client = new Client(this.url);
            Retrofit retrofit = client.getRetrofitClient();
            API api = retrofit.create(API.class);
            Call<DeleteCropModel> call = api.deleteCrop(cropId);
            call.enqueue(new DeleteCropCallback(this.context));
        }

        else if (i == DialogInterface.BUTTON_POSITIVE)
        {
            Integer cropId = this.list.get(this.index).getCropId();
            Client client = new Client(this.url);
            Retrofit retrofit = client.getRetrofitClient();
            API api = retrofit.create(API.class);
            Call<CreateCurrentCropModel> call = api.createCurrentCrop(cropId);
            call.enqueue(new CreateCurrentCropCallback(this.context));
        }
    }
}
