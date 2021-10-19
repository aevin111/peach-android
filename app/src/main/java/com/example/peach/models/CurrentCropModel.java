package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentCropModel
{
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("plant_date")
    @Expose
    private String plantDate;
    @SerializedName("crop_name")
    @Expose
    private String cropName;
    @SerializedName("plant_id")
    @Expose
    private Integer plantId;
    @SerializedName("crop_id")
    @Expose
    private Integer cropId;
    @SerializedName("maturity_integer")
    @Expose
    private Integer maturityInteger;

    public String getName()
    {
        return name;
    }

    public String getCategory()
    {
        return category;
    }

    public String getPlantDate()
    {
        return plantDate;
    }

    public String getCropName()
    {
        return cropName;
    }

    public Integer getPlantId()
    {
        return plantId;
    }

    public Integer getCropId()
    {
        return cropId;
    }

    public Integer getMaturityInteger()
    {
        return maturityInteger;
    }
}
