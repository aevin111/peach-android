package com.example.peach.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peach.R;
import com.example.peach.listeners.MainListener;

public class MainActivity extends AppCompatActivity
{
    private Button loginButton;
    private EditText passwordEditText;
    private EditText ipEditText;

    private void loadUI()
    {
        Log.i(this.getClass().getSimpleName(), "Loading UI");
        this.loginButton = findViewById(R.id.loginButton);
        this.ipEditText = findViewById(R.id.ipEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
    }

    private void setListener()
    {
        Log.i(this.getClass().getSimpleName(), "Setting listener");
        this.loginButton.setOnClickListener(new MainListener(this, this.ipEditText, this.passwordEditText));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadUI();
        this.setListener();
    }
}
