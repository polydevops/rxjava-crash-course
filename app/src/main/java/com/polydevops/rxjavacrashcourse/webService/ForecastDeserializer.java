package com.polydevops.rxjavacrashcourse.webService;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;

import java.lang.reflect.Type;

/**
 * TODO: Add class header comment.
 */

public class ForecastDeserializer implements JsonDeserializer<ForecastResponse> {

    private Gson gson = new Gson();

    @Override
    public ForecastResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("ForecastDeserializer", json.toString());
        return gson.fromJson(json, ForecastResponse.class);
    }
}
