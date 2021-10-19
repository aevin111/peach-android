package com.example.peach.listeners;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class ClassificationsListener implements DialogInterface.OnClickListener
{
    private AlertDialog alertDialog;

    public ClassificationsListener(AlertDialog alertDialog)
    {
        this.alertDialog = alertDialog;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i)
    {
        this.alertDialog.dismiss();
    }
}
