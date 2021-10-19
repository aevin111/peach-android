package com.example.peach.listeners;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peach.callbacks.ClassificationsCallback;
import com.example.peach.interfaces.API;
import com.example.peach.models.ClassificationsModel;
import com.example.peach.models.SymptomModel;
import com.example.peach.rest.Client;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Symptom2Listener implements AdapterView.OnItemClickListener, View.OnClickListener
{
    private Context context;
    private Integer plantId;
    private List<SymptomModel> list;
    private ListView listView;
    private Set<String> symptomsHash;
    private String url;

    public Symptom2Listener(Context context, Integer plantId, List<SymptomModel> list, ListView listView, String url)
    {
        this.context = context;
        this.plantId = plantId;
        this.list = list;
        this.listView = listView;
        this.symptomsHash = new HashSet<>();
        this.url = url;
    }

    private String convertSymptoms()
    {
        String[] hashString = this.symptomsHash.toArray(new String[this.symptomsHash.size()]);
        return Arrays.toString(hashString);
    }

    private void fetch()
    {
        Client client = new Client(this.url);
        Retrofit retrofit = client.getRetrofitClient();
        API api = retrofit.create(API.class);
        Call<ClassificationsModel> call = api.getDisease(this.plantId, this.convertSymptoms());
        call.enqueue(new ClassificationsCallback(this.context));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        SparseBooleanArray spa = this.listView.getCheckedItemPositions();
        int spaIndex = 0;

        while (spaIndex < spa.size())
        {
            if (spa.valueAt(spaIndex))
            {
                if (!this.symptomsHash.contains(this.list.get(spa.keyAt(spaIndex)).getSymptom()))
                {
                    this.symptomsHash.add(this.list.get(spa.keyAt(spaIndex)).getSymptom());
                }
            }

            else
            {
                this.symptomsHash.remove(this.list.get(spa.keyAt(spaIndex)).getSymptom());
            }

            spaIndex++;
        }
    }

    @Override
    public void onClick(View view)
    {
        this.fetch();
    }
}
