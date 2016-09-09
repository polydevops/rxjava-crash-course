package com.polydevops.rxjavacrashcourse.database;

import android.provider.BaseColumns;

/**
 * TODO: Add class header comment.
 */
public class WeatherTable implements BaseColumns {

    public static final String TABLE = "weather";

    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_TEMPERATURE = "temperature";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_HUMIDITY = "humidity";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE + " (" +
            COLUMN_DATE + " NUMBER NOT NULL, " +
            COLUMN_LOCATION + " TEXT NOT NULL, " +
            COLUMN_TEMPERATURE + " NUMBER NOT NULL, " +
            COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            COLUMN_HUMIDITY + " NUMBER NOT NULL" +
            ");";

    public static final String SQL_DELETE_TABLE = "DELETE FROM " + TABLE + ";";
}
