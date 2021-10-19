package com.example.peach.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassificationsModel
{
    @SerializedName("classifications")
    @Expose
    private List<String> classifications;

    public List<String> getClassifications()
    {
        return this.classifications;
    }
}
