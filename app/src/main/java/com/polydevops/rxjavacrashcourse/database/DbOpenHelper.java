package com.polydevops.rxjavacrashcourse.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * TODO: Add class header comment.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String NAME = "weather.db";
    public static final int VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ForecastTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // no impl
    }
}
