package com.polydevops.rxjavacrashcourse.webService;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.polydevops.rxjavacrashcourse.model.weather.WeatherResponse;

import java.lang.reflect.Type;

/**
 * TODO: Add class header comment.
 */
public class WeatherDeserializer implements JsonDeserializer<WeatherResponse> {

    private Gson gson = new Gson();

    @Override
    public WeatherResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("WeatherDeserializer", json.toString());
        return gson.fromJson(json, WeatherResponse.class);
    }
}
