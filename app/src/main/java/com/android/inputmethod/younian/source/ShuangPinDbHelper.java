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
            // Default values
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item("11", "q"));
            items.add(new Item("12", "w"));
            items.add(new Item("13", "e"));
            items.add(new Item("14", "r"));
            items.add(new Item("15", "t"));
            items.add(new Item("16", "y"));
            items.add(new Item("17", "sh"));
            items.add(new Item("18", "ch"));
            items.add(new Item("19", "p"));
            items.add(new Item("20", "a"));
            items.add(new Item("21", "s"));
            items.add(new Item("22", "d"));
            items.add(new Item("23", "f"));
            items.add(new Item("24", "g"));
            items.add(new Item("25", "h"));
            items.add(new Item("26", "j"));
            items.add(new Item("27", "k"));
            items.add(new Item("28", "l"));
            items.add(new Item("29", "ing"));
            items.add(new Item("30", "z"));
            items.add(new Item("31", "x"));
            items.add(new Item("32", "ch"));
            items.add(new Item("33", "zh"));
            items.add(new Item("34", "b"));
            items.add(new Item("35", "n"));
            items.add(new Item("36", "m"));
            items.add(new Item("37", "iu"));
            items.add(new Item("38", "ia"));
            items.add(new Item("39", "uan"));
            items.add(new Item("40", "ue"));
            items.add(new Item("41", "uai"));
            items.add(new Item("42", "ia"));
            items.add(new Item("43", "o"));
            items.add(new Item("44", "un"));
            items.add(new Item("45", "ong"));
            items.add(new Item("46", "uang"));
            items.add(new Item("47", "en"));
            items.add(new Item("48", "eng"));
            items.add(new Item("49", "ang"));
            items.add(new Item("50", "an"));
            items.add(new Item("51", "ao"));
            items.add(new Item("52", "ai"));
            items.add(new Item("53", "ei"));
            items.add(new Item("54", "ie"));
            items.add(new Item("55", "iao"));
            items.add(new Item("56", "ui"));
            items.add(new Item("57", "ou"));
            items.add(new Item("58", "ing"));
            items.add(new Item("59", "ian"));
            items.add(new Item("60", "ua"));
            items.add(new Item("61", "er"));
            items.add(new Item("62", "uo"));
            items.add(new Item("63", "iong"));
            items.add(new Item("64", "iang"));
            instance.insertItems(items);
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

    public boolean insertItems(ArrayList<Item> items) {
        SQLiteDatabase db = getWritableDatabase();

        boolean isSuccess = true;
        db.beginTransaction();
        try{
            for(Item item : items) {
                ContentValues values = new ContentValues();
                values.put(ShuangPinEntry.COLUMN_NAME_KEY, item.key);
                values.put(ShuangPinEntry.COLUMN_NAME_VALUE, item.value);
                db.insertOrThrow(ShuangPinEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            isSuccess = false;
        } finally {
            db.endTransaction();
        }
        return isSuccess;
    }

    public boolean insertItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();

        boolean isSuccess = true;
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(ShuangPinEntry.COLUMN_NAME_KEY, item.key);
            values.put(ShuangPinEntry.COLUMN_NAME_VALUE, item.value);
            db.insertOrThrow(ShuangPinEntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            isSuccess = false;
        } finally {
            db.endTransaction();
        }
        return isSuccess;
    }

    public void updateItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String[] args = {item.getKey()};
            ContentValues values = new ContentValues();
            values.put(ShuangPinEntry.COLUMN_NAME_VALUE, item.value);
            db.update(ShuangPinEntry.TABLE_NAME, values, ShuangPinEntry.COLUMN_NAME_KEY + " = ?", args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public void deleteItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String[] args = {item.getKey()};
            db.delete(ShuangPinEntry.TABLE_NAME, ShuangPinEntry.COLUMN_NAME_KEY + " = ?", args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public Item findItem(String key) {
        SQLiteDatabase db = getReadableDatabase();
        Item item = null;
        db.beginTransaction();
        try {
            String[] args = {key};
            Cursor cursor = db.rawQuery("SELECT * FROM " + ShuangPinEntry.TABLE_NAME + " WHERE " + ShuangPinEntry.COLUMN_NAME_KEY + " = ? ", args);
            if (cursor.moveToFirst()) {
                item = new Item(key, cursor.getString(cursor.getColumnIndex(ShuangPinEntry.COLUMN_NAME_VALUE)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            db.endTransaction();
        }
        return item;
    }
}

