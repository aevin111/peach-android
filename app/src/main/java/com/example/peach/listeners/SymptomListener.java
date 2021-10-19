package com.example.peach.listeners;

import java.util.List;

import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.peach.models.SymptomModel;

public class SymptomListener implements CompoundButton.OnCheckedChangeListener
{
    private List<SymptomModel> list;
    private ListView listView;
    private String url;

    public SymptomListener(ListView listView, String url)
    {
        this.listView = listView;
        this.url = url;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        int position = this.listView.getPositionForView(compoundButton);

        if (position != ListView.INVALID_POSITION)
        {

        }
    }
}
