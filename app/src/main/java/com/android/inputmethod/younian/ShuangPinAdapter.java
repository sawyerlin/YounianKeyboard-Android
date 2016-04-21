package com.android.inputmethod.younian;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.inputmethod.younian.source.Item;
import com.android.inputmethod.younian.source.ShuangPinDbHelper;

import java.util.ArrayList;

public class ShuangPinAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Item> data;
    private ShuangPinDbHelper helper;

    public ShuangPinAdapter(Context context, int layoutResourceId, ArrayList<Item> data, ShuangPinDbHelper helper) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.helper = helper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if (gridView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            gridView = inflater.inflate(layoutResourceId, parent, false);
        }
        TextView textViewKey = (TextView)gridView.findViewById(R.id.textViewKey);
        SliEditText editTextValue = (SliEditText)gridView.findViewById(R.id.editTextValue);
        Button buttonOk = (Button)gridView.findViewById(R.id.buttonOk);
        Button buttonDel = (Button)gridView.findViewById(R.id.buttonDel);
        buttonDel.setTag(new Integer(position));
        buttonOk.setTag(new Integer(position));
        Item item = data.get(position);
        textViewKey.setText(item.getKey());
        editTextValue.setText(item.getValue());
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tag = (Integer)view.getTag();
                Item item = data.get(tag.intValue());
                TextView editTextValue = (TextView)((View)view.getParent()).findViewById(R.id.editTextValue);
                item.setValue(editTextValue.getText().toString());
                helper.updateItem(item);
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tag = (Integer)view.getTag();
                helper.deleteItem(data.get(tag.intValue()));
                remove(getItem(tag.intValue()));
            }
        });
        return gridView;
    }
}
