package com.polydevops.rxjavacrashcourse.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

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

    public void showActivity(FragmentActivity activity, Class activityClass, Bundle bundle) {
        final Intent intent = new Intent();
        intent.setClass(activity, activityClass);
        intent.putExtras(bundle);

        activity.startActivity(intent);
        activity.finish();
    }

    public void goBack(FragmentActivity activity) {
        activity
                .getSupportFragmentManager()
                .popBackStack();
    }

    public Fragment getCurrentFragment(FragmentActivity activity) {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();

        final List<Fragment> fragments = fragmentManager.getFragments();

        if (fragments != null && !fragments.isEmpty()) {
            final int currentFragmentIndex = fragments.size() - 1;
            return fragments.get(currentFragmentIndex);
        } else {
            return null;
        }
    }
}
