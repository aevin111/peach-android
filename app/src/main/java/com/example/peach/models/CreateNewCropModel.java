package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateNewCropModel
{
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage()
    {
        return message;
    }
}
