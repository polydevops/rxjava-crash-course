package com.polydevops.rxjavacrashcourse.forecast;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.polydevops.rxjavacrashcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TODO: Add class header comment.
 */
public class ForecastDialog extends DialogFragment {

    public static final String TAG = "ForecastDialog";

    private static final String ARG_CITY = "arg_city";

    // region ButterKnife Binds
    @BindView(R.id.edit_forecast_city)
    EditText bEditForecastCity;
    // endregion

    private OnForecastDialogConfirmationButtonClickedListener confirmationButtonClickListener;

    public static ForecastDialog newInstance(String city) {
        final Bundle args = new Bundle();
        args.putString(ARG_CITY, city);

        final ForecastDialog dialog = new ForecastDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_forecast, null);
        ButterKnife.bind(this, view);

        setForecastCity();

        builder
                .setView(view)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (confirmationButtonClickListener != null) {
                            confirmationButtonClickListener.onConfirmationButtonClicked(bEditForecastCity.getText().toString());
                        }
                        dismiss();
                    }
                });

        return builder.create();
    }

    private void setForecastCity() {
        final Bundle args = getArguments();
        final String city = getArguments().getString(ARG_CITY);
        bEditForecastCity.setText(city);
    }

    public ForecastDialog setOnDialogConfirmationButtonClickedListener(final OnForecastDialogConfirmationButtonClickedListener listener) {
        this.confirmationButtonClickListener = listener;
        return this;
    }

    public interface OnForecastDialogConfirmationButtonClickedListener {
        void onConfirmationButtonClicked(String city);
    }

}
