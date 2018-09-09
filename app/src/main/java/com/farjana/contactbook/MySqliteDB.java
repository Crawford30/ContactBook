package com.farjana.contactbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteDB extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "contact.db";
    public static final String TABLE_NAME = "mytable";
    public static final String COLUMN1 = "id";
    public static final String COLUMN2 = "name";
    public static final String COLUMN3 = "cell";

    public MySqliteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query = "CREATE TABLE " + TABLE_NAME + " (id integer primary key,name text,cell text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE if EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //for insert data
    public boolean addToTable(String id, String name, String cell) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, id);
        values.put(COLUMN2, name);
        values.put(COLUMN3, cell);

        long check = db.insert(TABLE_NAME, null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor ViewData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result;
    }

    //for insert data
    public boolean updateData(String id, String name, String cell) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, id);
        values.put(COLUMN2, name);
        values.put(COLUMN3, cell);

        long check = db.update(TABLE_NAME, values, "id=?", new String[]{id});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int deleteData(String id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,"id=?",new String[]{id});
    }


}