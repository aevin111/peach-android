package com.example.peach.listeners;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.peach.R;
import com.example.peach.activities.ByDateChecklistActivity;
import com.example.peach.activities.TodayChecklistActivity;

public class CalendarListener implements CalendarView.OnDateChangeListener, View.OnClickListener
{
    private Button byDateButton;
    private Context context;
    private String date;
    private String url;

    public CalendarListener(Button byDateButton, Context context, String url)
    {
        this.byDateButton = byDateButton;
        this.context = context;
        this.url = url;
    }

    private void startNextIntent(String choice)
    {
        Intent intent = null;

        if (choice.equals("ByDateChecklistActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting ByDateChecklistActivity");
            intent = new Intent(this.context, ByDateChecklistActivity.class);
            intent.putExtra("url", this.url);
            intent.putExtra("date", this.date);
            this.context.startActivity(intent);
        }

        else if (choice.equals("TodayChecklistActivity"))
        {
            Log.i(this.getClass().getSimpleName(), " Starting TodayChecklistActivity");
            intent = new Intent(this.context, TodayChecklistActivity.class);
            intent.putExtra("url", this.url);
            intent.putExtra("date", this.date);
            this.context.startActivity(intent);
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.byDateButton)
        {
            this.startNextIntent("ByDateChecklistActivity");
        }

        else if (view.getId() == R.id.todayButton)
        {
            this.startNextIntent("TodayChecklistActivity");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth)
    {
        this.byDateButton.setEnabled(true);
        String dateString = Integer.toString(year) + "-" + Integer.toString(month + 1) + "-" + Integer.toString(dayOfMonth);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(dateString, formatter1).format(formatter2) + " " + "00:00:00.561798";
    }
}
