// Generated code from Butter Knife. Do not modify!
package com.polydevops.rxjavacrashcourse.weather;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.polydevops.rxjavacrashcourse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WeatherFragment_ViewBinding<T extends WeatherFragment> implements Unbinder {
  protected T target;

  private View view2131492986;

  @UiThread
  public WeatherFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.bEditZipCode = Utils.findRequiredViewAsType(source, R.id.edit_city, "field 'bEditZipCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_get_weather, "field 'bBtnGetWeather' and method 'onGetWeatherButtonClicked'");
    target.bBtnGetWeather = Utils.castView(view, R.id.btn_get_weather, "field 'bBtnGetWeather'", Button.class);
    view2131492986 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGetWeatherButtonClicked();
      }
    });
    target.bTextLocation = Utils.findRequiredViewAsType(source, R.id.text_weather_location, "field 'bTextLocation'", TextView.class);
    target.bTextTemperature = Utils.findRequiredViewAsType(source, R.id.text_weather_temperature, "field 'bTextTemperature'", TextView.class);
    target.bTextDescription = Utils.findRequiredViewAsType(source, R.id.text_weather_description, "field 'bTextDescription'", TextView.class);
    target.bTextHumidity = Utils.findRequiredViewAsType(source, R.id.text_weather_humidity, "field 'bTextHumidity'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bEditZipCode = null;
    target.bBtnGetWeather = null;
    target.bTextLocation = null;
    target.bTextTemperature = null;
    target.bTextDescription = null;
    target.bTextHumidity = null;

    view2131492986.setOnClickListener(null);
    view2131492986 = null;

    this.target = null;
  }
}
