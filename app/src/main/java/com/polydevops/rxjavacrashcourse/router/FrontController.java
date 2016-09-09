package com.polydevops.rxjavacrashcourse.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Provides navigation for application
 */
public class FrontController {

    private FragmentMap fragmentMap;

    public FrontController(FragmentMap fragmentMap) {
        this.fragmentMap = fragmentMap;
    }

    public void showFragment(FragmentActivity activity, String fragmentTag, Bundle bundle, int containerLayoutId) {
        final Fragment fragment = fragmentMap.getFragment(fragmentTag);

        if (fragment != null) {

            fragment.setArguments(bundle);

            activity
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerLayoutId, fragment)
                    .addToBackStack(fragmentTag)
                    .commit();
        }
    }

    public void showActivity(AppCompatActivity activity, Class activityClass, Bundle bundle) {
        final Intent intent = new Intent();
        intent.setClass(activity, activityClass);
        intent.putExtras(bundle);

        activity.startActivity(intent);
        activity.finish();
    }
}
