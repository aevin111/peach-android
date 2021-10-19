package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummarizedPlantsListModel
{
    @SerializedName("plant_id")
    @Expose
    private Integer plantId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("time_planting")
    @Expose
    private String timePlanting;

    public Integer getPlantId()
    {
        return this.plantId;
    }

    @Override
    public String toString()
    {
        return "Plant ID: " + this.plantId.toString() + "\n" + "Name: " + this.name + "\n" + "Category: " + this.category + "\n" + "Time Planting: " + this.timePlanting + "\n";
    }
}
