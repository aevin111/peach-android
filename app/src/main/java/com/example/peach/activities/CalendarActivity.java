package com.example.peach.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.listeners.CalendarListener;

public class CalendarActivity extends AppCompatActivity
{
    private Button calendarBackButton;
    private Button byDateButton;
    private Button todayButton;
    private CalendarView calendarView;
    private String url;

    private void getIntentData()
    {
        Log.i(this.getClass().getSimpleName(), " Getting intent data");
        Intent intent = getIntent();
        this.url = intent.getStringExtra("url");
    }

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.calendarView = findViewById(R.id.calendarView);
        this.byDateButton = findViewById(R.id.byDateButton);
        this.todayButton = findViewById(R.id.todayButton);
        this.calendarBackButton = findViewById(R.id.calendarBackButton);
        this.calendarBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CalendarActivity.super.finish();
            }
        });
    }

    private void setListener()
    {
        Log.i(this.getClass().getSimpleName(), "Setting listener...");
        CalendarListener listener = new CalendarListener(this.byDateButton,this, this.url);
        this.calendarView.setOnDateChangeListener(listener);
        this.byDateButton.setOnClickListener(listener);
        this.byDateButton.setEnabled(false);
        this.todayButton.setOnClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.getIntentData();
        this.loadUI();
        this.setListener();
    }
}
