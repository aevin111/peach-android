package com.example.peach.listeners;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class DeleteCropListener implements DialogInterface.OnClickListener
{
    private AlertDialog alertDialog;

    public DeleteCropListener(AlertDialog alertDialog)
    {
        this.alertDialog = alertDialog;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i)
    {
        alertDialog.dismiss();
    }
}
