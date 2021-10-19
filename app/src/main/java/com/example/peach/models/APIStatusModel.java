package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIStatusModel
{
    @SerializedName("response")
    @Expose
    private Integer response;

    public Integer getResponse()
    {
        return response;
    }
}
