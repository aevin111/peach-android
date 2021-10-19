package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropsListModel
{
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("plant_date")
    @Expose
    private String plantDate;
    @SerializedName("plant_id")
    @Expose
    private Integer plantId;
    @SerializedName("crop_id")
    @Expose
    private Integer cropId;

    public String getName()
    {
        return name;
    }

    public String getPlantDate()
    {
        return plantDate;
    }

    public String getCropName()
    {
        return name;
    }

    public Integer getPlantId()
    {
        return plantId;
    }

    public Integer getCropId()
    {
        return cropId;
    }

    @Override
    public String toString()
    {
        return "Crop ID: " + this.cropId.toString() + "\n" + "Crop Name: " + this.name + "\n" + "Plant Date: " + this.plantDate + "\n" + "Plant ID: " + this.plantId.toString() + "\n";
    }
}

/*
crop_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    plant_id INTEGER,
    name TEXT,
    plant_date TEXT,
    FOREIGN KEY(plant_id) REFERENCES plants(plant_id)
 */
