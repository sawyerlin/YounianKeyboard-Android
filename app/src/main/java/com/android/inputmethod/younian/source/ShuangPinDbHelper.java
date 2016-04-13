package com.android.inputmethod.younian.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class ShuangPinDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ShuangPin.db";
    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_SHUANGPINS =
            "CREATE TABLE " + ShuangPinEntry.TABLE_NAME + " (" +
                    ShuangPinEntry.COLUMN_NAME_KEY + " " + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    ShuangPinEntry.COLUMN_NAME_VALUE + " " + TEXT_TYPE  +
                    " )";
    private static final String SQL_DELETE_SHUANGPIN =
            "DROP TABLE IF EXISTS " + ShuangPinEntry.TABLE_NAME;

    public static abstract class ShuangPinEntry implements BaseColumns {
        public static final String TABLE_NAME = "ShuangPin";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_VALUE = "value";
    }

    private static ShuangPinDbHelper instance;

    public static synchronized ShuangPinDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ShuangPinDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    private ShuangPinDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_SHUANGPINS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_SHUANGPIN);
        onCreate(sqLiteDatabase);
    }

    public void insertItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(ShuangPinEntry.COLUMN_NAME_KEY, item.key);
            values.put(ShuangPinEntry.COLUMN_NAME_VALUE, item.value);
            db.insertOrThrow(ShuangPinEntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public void updateItem() {
    }

    public void deleteItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String[] args = {item.getKey()};
            db.delete(ShuangPinEntry.TABLE_NAME, ShuangPinEntry.COLUMN_NAME_KEY + " = ?", args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            db.endTransaction();
        }

    }

    public ArrayList<Item> findItems() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Item> items = new ArrayList<Item>();
        db.beginTransaction();
        System.out.println("find items");
        try {
            System.out.println("find items begin");
            Cursor cursor = db.rawQuery("SELECT * FROM " + ShuangPinEntry.TABLE_NAME, null);
            System.out.println("find items after");
            if (cursor.moveToFirst()) {
                System.out.println("move to first");
                do {
                    System.out.println(cursor.getString(cursor.getColumnIndex(ShuangPinEntry.COLUMN_NAME_KEY)));
                    Item item = new Item(cursor.getString(cursor.getColumnIndex(ShuangPinEntry.COLUMN_NAME_KEY)),
                            cursor.getString(cursor.getColumnIndex(ShuangPinEntry.COLUMN_NAME_VALUE)));
                    items.add(item);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            db.endTransaction();
        }
        return items;
    }

    public Item findItem() {
        return null;
    }
}

