package com.polydevops.rxjavacrashcourse.dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * TODO: Add class header comment.
 */

public class LoadingDialog extends ProgressDialog {

    public LoadingDialog(final Context context, final int messageStringResId) {
        super(context);
        final String message = getContext().getString(messageStringResId);
        setMessage(message);
    }

    public LoadingDialog(final Context context, final String message) {
        super(context);
        setMessage(message);
    }

    public static LoadingDialog newInstance(final Context context, final String message) {
        return new LoadingDialog(context, message);
    }

    public static LoadingDialog newInstance(final Context context, final int messageStringResId) {
        return new LoadingDialog(context, messageStringResId);
    }

}
