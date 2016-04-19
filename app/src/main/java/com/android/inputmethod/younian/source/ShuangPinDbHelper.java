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
        // Default values
        insertItem(new Item("11", "q"));
        insertItem(new Item("12", "w"));
        insertItem(new Item("13", "e"));
        insertItem(new Item("14", "r"));
        insertItem(new Item("15", "t"));
        insertItem(new Item("16", "y"));
        insertItem(new Item("17", "sh"));
        insertItem(new Item("18", "ch"));
        insertItem(new Item("19", "p"));
        insertItem(new Item("20", "a"));
        insertItem(new Item("21", "s"));
        insertItem(new Item("22", "d"));
        insertItem(new Item("23", "f"));
        insertItem(new Item("24", "g"));
        insertItem(new Item("25", "h"));
        insertItem(new Item("26", "j"));
        insertItem(new Item("27", "k"));
        insertItem(new Item("28", "l"));
        insertItem(new Item("29", "ing"));
        insertItem(new Item("30", "z"));
        insertItem(new Item("31", "x"));
        insertItem(new Item("32", "ch"));
        insertItem(new Item("33", "zh"));
        insertItem(new Item("34", "b"));
        insertItem(new Item("35", "n"));
        insertItem(new Item("36", "m"));
        insertItem(new Item("37", "iu"));
        insertItem(new Item("38", "ia"));
        insertItem(new Item("39", "uan"));
        insertItem(new Item("40", "ue"));
        insertItem(new Item("41", "uai"));
        insertItem(new Item("42", "ia"));
        insertItem(new Item("43", "o"));
        insertItem(new Item("44", "un"));
        insertItem(new Item("45", "ong"));
        insertItem(new Item("46", "uang"));
        insertItem(new Item("47", "en"));
        insertItem(new Item("48", "eng"));
        insertItem(new Item("49", "ang"));
        insertItem(new Item("50", "an"));
        insertItem(new Item("51", "ao"));
        insertItem(new Item("52", "ai"));
        insertItem(new Item("53", "ei"));
        insertItem(new Item("54", "ie"));
        insertItem(new Item("55", "iao"));
        insertItem(new Item("56", "ui"));
        insertItem(new Item("57", "ou"));
        insertItem(new Item("58", "ing"));
        insertItem(new Item("59", "ian"));
        insertItem(new Item("60", "ua"));
        insertItem(new Item("61", "er"));
        insertItem(new Item("62", "uo"));
        insertItem(new Item("63", "iong"));
        insertItem(new Item("64", "iang"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_SHUANGPIN);
        onCreate(sqLiteDatabase);
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

