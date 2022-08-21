package com.example.sqlitedatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqlitedatabase.R;
import com.example.sqlitedatabase.entity.Information;

import java.util.List;

public class AdapterInformation extends ArrayAdapter<Information> {
    private Context mCtx;
    private List<Information> mLst;

    public AdapterInformation( Context context, List<Information> objects) {
        super(context, R.layout.item_information, objects);
        this.mCtx = context;
        this.mLst = objects;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(this.mCtx).inflate(R.layout.item_information, null);
        }
        TextView txtId = item.findViewById(R.id.itemID);
        TextView txtName = item.findViewById(R.id.itemName);
        TextView txtNumber = item.findViewById(R.id.itemNumber);
        TextView txtEmail = item.findViewById(R.id.itemEmail);

        Information i = mLst.get(position);

        txtId.setText(i.getID() + "");
        txtName.setText(i.getName());
        txtNumber.setText(i.getNumber());
        txtEmail.setText(i.getEmail());
        return item;
    }
}
