package com.polydevops.rxjavacrashcourse.database;

import android.provider.BaseColumns;

/**
 * TODO: Add class header comment.
 */
public class ForecastTable implements BaseColumns {

    public static final String TABLE = "forecast";

    public static final String COLUMN_DATE = "date";

    public static final String COLUMN_MINIMUM_TEMPERATURE = "minimum_temperature";
    public static final String COLUMN_MAXIMUM_TEMPERATURE = "maximum_temperature";
    public static final String COLUMN_DAY_TEMPERATURE = "day_temperature";
    public static final String COLUMN_NIGHT_TEMPERATURE = "night_temperature";
    public static final String COLUMN_EVENING_TEMPERATURE = "evening_temperature";
    public static final String COLUMN_MORNING_TEMPERATURE = "morning_temperature";

    public static final String COLUMN_WEATHER_ID = "weather_id";
    public static final String COLUMN_MAIN = "main";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ICON = "icon";

    public static final String COLUMN_HUMIDITY = "humidity";
    public static final String COLUMN_PRESSURE = "pressure";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE + " (" +
            COLUMN_DATE + " NUMBER NOT NULL, " +
            COLUMN_MINIMUM_TEMPERATURE + " REAL NOT NULL, " +
            COLUMN_MAXIMUM_TEMPERATURE + " REAL NOT NULL, " +
            COLUMN_DAY_TEMPERATURE + " REAL NOT NULL, " +
            COLUMN_NIGHT_TEMPERATURE + " REAL NOT NULL, " +
            COLUMN_EVENING_TEMPERATURE + " REAL NOT NULL, " +
            COLUMN_MORNING_TEMPERATURE + " REAL NOT NULL, " +
            COLUMN_WEATHER_ID + " TEXT NOT NULL, " +
            COLUMN_MAIN + " TEXT NOT NULL, " +
            COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            COLUMN_ICON + " NUMBER NOT NULL, " +
            COLUMN_HUMIDITY + " NUMBER NOT NULL, " +
            COLUMN_PRESSURE + " NUMBER NOT NULL" +
            ");";

    public static final String SQL_DELETE_TABLE = "DELETE FROM " + TABLE + ";";
}
