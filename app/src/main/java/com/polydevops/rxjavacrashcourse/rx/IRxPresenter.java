package com.polydevops.rxjavacrashcourse.rx;

import rx.subscriptions.CompositeSubscription;

/**
 * TODO: Add class header comment.
 */
public interface IRxPresenter {

    void onCreate();
    void onDestroy();
    CompositeSubscription getCompositeSubscription();
}
