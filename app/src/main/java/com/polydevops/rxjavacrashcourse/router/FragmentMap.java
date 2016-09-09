package com.polydevops.rxjavacrashcourse.router;

import android.support.v4.app.Fragment;

import com.polydevops.rxjavacrashcourse.home.HomeFragment;
import com.polydevops.rxjavacrashcourse.weather.WeatherFragment;

/**
 * Maps fragments based off their tag
 */
public class FragmentMap {

    public Fragment getFragment(final String tag) {
        switch (tag) {
            case HomeFragment.TAG:
                return HomeFragment.newInstance();
            case WeatherFragment.TAG:
                return WeatherFragment.newInstance();
            default:
                throw new UnsupportedOperationException("Invalid fragment tag");
        }
    }
}
