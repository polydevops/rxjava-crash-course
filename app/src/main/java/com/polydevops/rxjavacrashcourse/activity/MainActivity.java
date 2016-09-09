package com.polydevops.rxjavacrashcourse.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.RxJavaCrashCourseApplication;
import com.polydevops.rxjavacrashcourse.home.HomeFragment;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IToolbar {

    @BindView(R.id.toolbar)
    Toolbar bToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            RxJavaCrashCourseApplication.getFrontController()
                    .showFragment(this, HomeFragment.TAG, null, R.id.content_frame);
        }
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
