package com.polydevops.rxjavacrashcourse.forecast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastWeather;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TODO: Add class header comment.
 */
public class ForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEEE, MMMM d");

    private List<ForecastWeather> forecast;

    public ForecastAdapter() { }

    public ForecastAdapter(List<ForecastWeather> forecast) {
        this.forecast = forecast;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (forecast == null || forecast.size() == 0) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_forecast, null);
            return new EmptyForecastViewHolder(itemView);
        } else {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, null);
            return new ForecastViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ForecastViewHolder) {
            final ForecastWeather weatherForecast = forecast.get(position);

            final ForecastViewHolder forecastViewHolder = (ForecastViewHolder) holder;
            setDate(forecastViewHolder, weatherForecast.getDate());
            setTemperature(forecastViewHolder, weatherForecast.getMain().getHumidity());
            setHumidity(forecastViewHolder, weatherForecast.getMain().getHumidity());
        }
    }

    @Override
    public int getItemCount() {
        return (forecast != null) ? forecast.size() : 1;
    }

    private void setDate(final ForecastViewHolder holder, long utcDate) {
        final long millisecondsDate = TimeUnit.SECONDS.toMillis(utcDate);
        final String date = dateTimeFormatter.print(millisecondsDate);
        holder.bTextDate.setText(date);
    }

    private void setTemperature(final ForecastViewHolder holder, final double temperature) {
        final String formattedTemperature = temperature + "°";
        holder.bTextTemperature.setText(formattedTemperature);
    }

    private void setHumidity(final ForecastViewHolder holder, final int humidity) {
        final String formattedHumidity = humidity + "% Humidity";
        holder.bTextHumidity.setText(formattedHumidity);
    }

    public class EmptyForecastViewHolder extends RecyclerView.ViewHolder {
        public EmptyForecastViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_forecast_date)
        TextView bTextDate;

        @BindView(R.id.text_forecast_temperature)
        TextView bTextTemperature;

        @BindView(R.id.text_forecast_humidity)
        TextView bTextHumidity;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
