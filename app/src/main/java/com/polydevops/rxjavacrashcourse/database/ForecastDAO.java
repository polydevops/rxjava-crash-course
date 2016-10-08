package com.polydevops.rxjavacrashcourse.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.polydevops.rxjavacrashcourse.model.forecast.ForecastWeather;
import com.polydevops.rxjavacrashcourse.model.forecast.Temperature;
import com.polydevops.rxjavacrashcourse.model.weather.Weather;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * DAO (data access object) for managing a forecast cache
 */

public class ForecastDAO {

    private SQLiteOpenHelper db;

    public ForecastDAO(SQLiteOpenHelper db) {
        this.db = db;
    }

    public Observable<List<ForecastWeather>> getForecastCache() {
        return Observable.just(db.getReadableDatabase().query(ForecastTable.TABLE, null, null, null, null, null, null))
                .map(new Func1<Cursor, List<ForecastWeather>>() {
                    @Override
                    public List<ForecastWeather> call(Cursor cursor) {
                        return mapForecastWeatherCursor(cursor);
                    }
                });
    }

    public Observable<Integer> saveForecastCache(final List<ForecastWeather> forecast) {
        return Observable.just(bulkInsertForecastOperation(forecast));
    }

    public Observable<Integer> deleteForecastCache() {
        return Observable.just(db.getWritableDatabase().delete(ForecastTable.TABLE, null, null));
    }

    private List<ForecastWeather> mapForecastWeatherCursor(final Cursor cursor) {
        final List<ForecastWeather> forecast = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                forecast.add(mapCursorToForecastWeather(cursor));
            } while (cursor.moveToNext());

            return forecast;
        }

        return null;
    }

    private int bulkInsertForecastOperation(final List<ForecastWeather> forecast) {
        int rowsInserted = 0;
        for (ForecastWeather weather : forecast) {
            final long insertedId = db.getWritableDatabase().insert(ForecastTable.TABLE, null, mapForecastWeatherToContentValues(weather));
            if (insertedId > 0) {
                rowsInserted++;
            }
        }

        return rowsInserted;
    }

    private ContentValues mapForecastWeatherToContentValues(final ForecastWeather weather) {
        final ContentValues values = new ContentValues();

        values.put(ForecastTable.COLUMN_MINIMUM_TEMPERATURE, weather.getTemp().getMin());
        values.put(ForecastTable.COLUMN_MAXIMUM_TEMPERATURE, weather.getTemp().getMax());
        values.put(ForecastTable.COLUMN_DAY_TEMPERATURE, weather.getTemp().getDay());
        values.put(ForecastTable.COLUMN_NIGHT_TEMPERATURE, weather.getTemp().getNight());
        values.put(ForecastTable.COLUMN_EVENING_TEMPERATURE, weather.getTemp().getEve());
        values.put(ForecastTable.COLUMN_MORNING_TEMPERATURE, weather.getTemp().getMorn());

        values.put(ForecastTable.COLUMN_WEATHER_ID, weather.getWeather().getId());
        values.put(ForecastTable.COLUMN_MAIN, weather.getWeather().getMain());
        values.put(ForecastTable.COLUMN_DESCRIPTION, weather.getWeather().getDescription());
        values.put(ForecastTable.COLUMN_ICON, weather.getWeather().getIcon());

        values.put(ForecastTable.COLUMN_DATE, weather.getDate());
        values.put(ForecastTable.COLUMN_PRESSURE, weather.getPressure());
        values.put(ForecastTable.COLUMN_HUMIDITY, weather.getHumidity());

        return values;
    }

    private ForecastWeather mapCursorToForecastWeather(final Cursor cursor) {

        final Temperature temperature = new Temperature(
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_MINIMUM_TEMPERATURE)),
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_MAXIMUM_TEMPERATURE)),
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_DAY_TEMPERATURE)),
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_NIGHT_TEMPERATURE)),
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_EVENING_TEMPERATURE)),
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_MORNING_TEMPERATURE))
        );

        final Weather weather = new Weather(
                cursor.getInt(cursor.getColumnIndex(ForecastTable.COLUMN_WEATHER_ID)),
                cursor.getString(cursor.getColumnIndex(ForecastTable.COLUMN_MAIN)),
                cursor.getString(cursor.getColumnIndex(ForecastTable.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(ForecastTable.COLUMN_ICON))
        );

        final List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);

        return new ForecastWeather(
                cursor.getLong(cursor.getColumnIndex(ForecastTable.COLUMN_DATE)),
                temperature,
                cursor.getDouble(cursor.getColumnIndex(ForecastTable.COLUMN_PRESSURE)),
                cursor.getInt(cursor.getColumnIndex(ForecastTable.COLUMN_HUMIDITY)),
                weatherList
        );
    }


}
