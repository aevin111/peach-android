package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SensorStatusModel
{
    @SerializedName("moisture_level")
    @Expose
    private String moistureLevel;

    public String getMoistureLevel()
    {
        return this.moistureLevel;
    }
}
