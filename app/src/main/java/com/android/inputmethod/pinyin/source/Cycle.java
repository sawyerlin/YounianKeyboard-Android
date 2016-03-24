package com.android.inputmethod.pinyin.source;

import java.util.ArrayList;

public class Cycle<T> {

    private ArrayList<T> arrayList;
    private int position;

    public Cycle(ArrayList<T> arrayList, T t) {
        this.arrayList = arrayList;
        position = arrayList.indexOf(t);
    }

    public T peek() {
        int size = arrayList.size();
        this.position ++;
        if (this.position == size) {
            this.position = 0;
        }
        return arrayList.get(this.position);
    }
}
