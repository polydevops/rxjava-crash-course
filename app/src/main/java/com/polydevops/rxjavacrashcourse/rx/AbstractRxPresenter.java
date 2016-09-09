package com.polydevops.rxjavacrashcourse.rx;

import rx.subscriptions.CompositeSubscription;

/**
 * TODO: Add class header comment.
 */
public abstract class AbstractRxPresenter implements IRxPresenter {

    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate() {
        initCompositeSubscription();
    }

    @Override
    public void onDestroy() {
        destroyCompositeSubscription();
    }

    @Override
    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }

    private void initCompositeSubscription() {
        if (compositeSubscription == null || compositeSubscription.isUnsubscribed()) {
            compositeSubscription = new CompositeSubscription();
        }
    }

    private void destroyCompositeSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
