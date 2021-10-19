package com.example.peach.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantsSearchResultModel implements Serializable
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
    @SerializedName("maturity")
    @Expose
    private String maturity;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("soil")
    @Expose
    private String soil;
    @SerializedName("seeding_care")
    @Expose
    private String seedingCare;
    @SerializedName("transplanting")
    @Expose
    private String transplanting;
    @SerializedName("plant_care")
    @Expose
    private String plantCare;
    @SerializedName("harvesting")
    @Expose
    private String harvesting;
    @SerializedName("maturity_integer")
    @Expose
    private Integer maturityInteger;

    public String getInfo()
    {
        return  "Name: " + "\n" + this.name + "\n\n"
                + "Category: " + "\n" + this.category + "\n\n"
                + "Time Planting: " + "\n" + this.timePlanting + "\n\n"
                + "Maturity: " + "\n" + this.maturity + "\n\n"
                + "About " + "\n" + this.about + "\n\n"
                + "Soil: " + "\n" + this.soil + "\n\n"
                + "Seeding Care: " + "\n" + this.seedingCare + "\n\n"
                + "Transplanting: " + "\n" + this.transplanting + "\n\n"
                + "Plant Care: " + "\n" + this.plantCare + "\n\n"
                + "Harvesting: " + "\n" + this.harvesting;
    }

    @Override
    public String toString()
    {
        return "Plant ID: " + this.plantId.toString() + "\n"
                + "Name: " + this.name + "\n"
                + "Category: " + this.category + "\n"
                + "Time Planting: " + this.timePlanting + "\n";
    }
}
