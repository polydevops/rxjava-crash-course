// Generated code from Butter Knife. Do not modify!
package com.polydevops.rxjavacrashcourse.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.polydevops.rxjavacrashcourse.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'bToolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bToolbar = null;

    this.target = null;
  }
}
