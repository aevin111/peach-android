package com.example.peach.listeners;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.example.peach.models.CropsListModel;

public class SetCurrentCropListener implements AdapterView.OnItemClickListener
{
    private Context context;
    private List<CropsListModel> list;
    private String url;

    public SetCurrentCropListener(Context context, List<CropsListModel> list, String url)
    {
        this.context = context;
        this.list = list;
        this.url = url;
    }

    private void alert(int index)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Choose your action");
        builder.setMessage("What do you want to do?");
        AlertDialog alertDialog = builder.create();
        CropChoiceListener listener = new CropChoiceListener(this.context, alertDialog, this.list, index, this.url);
        builder.setPositiveButton("Set Current Crop", listener);
        builder.setNegativeButton("Delete", listener);
        builder.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        this.alert(i);
    }
}
