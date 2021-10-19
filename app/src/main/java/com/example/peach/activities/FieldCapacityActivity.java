package com.example.peach.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.listeners.FieldCapacityListener;

public class FieldCapacityActivity extends AppCompatActivity
{
    private Button fieldCapacityBackButton;
    private Button editFieldCapacityButton;
    private EditText fieldCapacityEditText;
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
        this.fieldCapacityBackButton = findViewById(R.id.fieldCapacityBackButton);
        this.editFieldCapacityButton = findViewById(R.id.editFieldCapacityButton);
        this.fieldCapacityEditText = findViewById(R.id.fieldCapacityEditText);
        this.editFieldCapacityButton.setOnClickListener(new FieldCapacityListener(this, fieldCapacityEditText, url));
        this.fieldCapacityBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FieldCapacityActivity.super.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_capacity);
        this.getIntentData();
        this.loadUI();
    }
}
