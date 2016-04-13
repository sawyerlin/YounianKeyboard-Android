package com.android.inputmethod.younian;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.android.inputmethod.younian.source.Item;
import com.android.inputmethod.younian.source.ShuangPinDbHelper;

import java.util.ArrayList;

public class ShuangPinActivity extends Activity {
    private ShuangPinDbHelper helper;
    private GridView gridView;
    private ArrayList<Item> gridArray;
    private ShuangPinAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuangpin_settings);
        helper = ShuangPinDbHelper.getInstance(this);
        gridView = (GridView) findViewById(R.id.gridView);
        gridArray = helper.findItems();
        adapter = new ShuangPinAdapter(this, R.layout.shuangpin_item, gridArray, helper);
        gridView.setAdapter(adapter);

        final EditText editTextKey = (EditText) findViewById(R.id.editTextKey);
        final EditText editTextValue = (EditText) findViewById(R.id.editTextValue);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Item item = new Item(editTextKey.getText().toString(), editTextValue.getText().toString());
                adapter.add(item);
                adapter.notifyDataSetChanged();
                helper.insertItem(item);
            }
        });
    }
}