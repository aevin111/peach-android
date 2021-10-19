package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentTaskModel
{
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("time_frame")
    @Expose
    private String timeFrame;

    @Override
    public String toString()
    {
        return "Description: " + this.description + "\n" + "Time: " + this.time + "\n" + "Time Frame: " + this.timeFrame + "\n";
    }
}
