// Generated code from Butter Knife. Do not modify!
package com.polydevops.rxjavacrashcourse.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.polydevops.rxjavacrashcourse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding<T extends HomeFragment> implements Unbinder {
  protected T target;

  private View view2131492987;

  private View view2131492988;

  @UiThread
  public HomeFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_weather_example, "method 'onWeatherExampleButtonPressed'");
    view2131492987 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onWeatherExampleButtonPressed();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_forecast_example, "method 'onForecastExampleButtonPressed'");
    view2131492988 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onForecastExampleButtonPressed();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131492987.setOnClickListener(null);
    view2131492987 = null;
    view2131492988.setOnClickListener(null);
    view2131492988 = null;

    this.target = null;
  }
}
