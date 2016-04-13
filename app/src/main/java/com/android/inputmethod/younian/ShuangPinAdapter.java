package com.android.inputmethod.younian;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
            final TextView textViewKey = (TextView)gridView.findViewById(R.id.textViewKey);
            final EditText editTextValue = (EditText)gridView.findViewById(R.id.editTextValue);
            Button buttonOk = (Button)gridView.findViewById(R.id.buttonOk);
            Button buttonDel = (Button)gridView.findViewById(R.id.buttonDel);
            System.out.println(data.size());
            System.out.println(data.get(data.size() - 1));
            System.out.println(position);
            Item item = data.get(position);
            textViewKey.setText(item.getKey());
            editTextValue.setText(item.getValue());
            final int currentPosition = position;
            final ShuangPinAdapter adapter = this;
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            buttonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.data.remove(currentPosition);
                    adapter.notifyDataSetChanged();
                    helper.deleteItem(data.get(currentPosition));
                }
            });
        }
        return gridView;
    }
}
