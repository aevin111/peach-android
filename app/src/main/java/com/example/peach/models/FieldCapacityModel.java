package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldCapacityModel
{
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage()
    {
        return this.message;
    }
}
