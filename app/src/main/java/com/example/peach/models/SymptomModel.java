package com.example.peach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SymptomModel
{
    private boolean selected = true;
    @SerializedName("symptom")
    @Expose
    private String symptom;

    public String getSymptom()
    {
        return this.symptom;
    }

    public boolean isSelected()
    {
        return this.selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public String toString()
    {
        return this.symptom;
    }
}
