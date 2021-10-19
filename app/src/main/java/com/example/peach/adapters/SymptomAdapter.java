//https://androidcource.blogspot.com/2018/07/listview-with-checkbox-in-android.html
package com.example.peach.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.peach.R;
import com.example.peach.holders.SymptomHolder;
import com.example.peach.listeners.SymptomListener;
import com.example.peach.models.SymptomModel;

public class SymptomAdapter extends ArrayAdapter<SymptomModel>
{
    private Context context;
    private List<SymptomModel> list;
    private ListView listView;
    private String url;

    public SymptomAdapter(@NonNull Context context, int resource, List<SymptomModel> list, String url, ListView listView)
    {
        super(context, resource);
        this.context = context;
        this.list = list;
        this.url = url;
        this.listView = listView;
        list.addAll(list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        SymptomHolder holder = new SymptomHolder();

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row,null);
            holder.checkBox = convertView.findViewById(R.id.symptomCheckbox);
            holder.textView = convertView.findViewById(R.id.symptomTextView);
            holder.checkBox.setOnCheckedChangeListener(new SymptomListener(this.listView, this.url));
            convertView.setTag(holder);
        }

        else
        {
            holder = (SymptomHolder) convertView.getTag();
        }

        SymptomModel model = list.get(position);
        holder.textView.setText(model.getSymptom());
        holder.checkBox.setTag(list);
        return convertView;
    }

}
