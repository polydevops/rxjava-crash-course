package com.polydevops.rxjavacrashcourse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.RxJavaCrashCourseApplication;
import com.polydevops.rxjavacrashcourse.home.HomeFragment;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.view.IOnBackPressed;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IToolbar {

    @BindView(R.id.toolbar)
    Toolbar bToolbar;

    private ActionBar actionBar;

    private FrontController frontController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(bToolbar);
        actionBar = getSupportActionBar();

        frontController = RxJavaCrashCourseApplication.getFrontController();

        if (savedInstanceState == null) {
            frontController
                    .showFragment(this, HomeFragment.TAG, null, R.id.content_frame);
        }
    }

    @Override
    public void onBackPressed() {
        final Fragment fragment = frontController.getCurrentFragment(this);
        if (fragment instanceof IOnBackPressed) {
            ((IOnBackPressed) fragment).onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void displayBackNav(boolean enabled) {
        actionBar.setDisplayHomeAsUpEnabled(enabled);
    }

    @Override
    public void setToolbarTitle(int titleResId) {
        bToolbar.setTitle(titleResId);
    }

    @Override
    public void setToolbarTitle(String title) {
        bToolbar.setTitle(title);
    }
}
