package com.polydevops.rxjavacrashcourse;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.polydevops.rxjavacrashcourse.router.FragmentMap;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.webService.WebServiceManager;

/**
 * Custom application for the app. Holds common dependencies, such as the FrontController
 */
public class RxJavaCrashCourseApplication extends Application {

    private static FrontController frontController;
    private static WebServiceManager webServiceManager;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        frontController = new FrontController(new FragmentMap());
        webServiceManager = new WebServiceManager();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static FrontController getFrontController() {
        return frontController;
    }

    public static WebServiceManager getWebServiceManager() {
        return webServiceManager;
    }

    public static SharedPreferences getSharedPreferences() { return sharedPreferences; }
}
