package com.polydevops.rxjavacrashcourse.forecast;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.RxJavaCrashCourseApplication;
import com.polydevops.rxjavacrashcourse.database.ForecastDAO;
import com.polydevops.rxjavacrashcourse.dialog.LoadingDialog;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.view.IOnBackPressed;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment implements ForecastContract.View, IOnBackPressed {

    public static final String TAG = "ForecastFragment";

    // region ButterKnife Binds
    @BindView(R.id.text_forecast_location)
    TextView bLocationTextView;

    @BindView(R.id.recycler_forecast)
    RecyclerView bRecyclerView;
    // endregion

    private IToolbar toolbar;
    private LoadingDialog loadingDialog;

    private ForecastContract.Presenter presenter;

    public ForecastFragment() {
        // Required empty public constructor
    }

    public static ForecastFragment newInstance() {
        return new ForecastFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            toolbar = (IToolbar) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Please have activity implement IToolbar.");
        }
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        final ForecastContract.View view = this;
        final ForecastContract.Interactor interactor = new ForecastInteractor(
                RxJavaCrashCourseApplication.getWebServiceManager(),
                RxJavaCrashCourseApplication.getSharedPreferences(),
                new ForecastDAO(RxJavaCrashCourseApplication.getSQLiteOpenHelper()));
        final FrontController frontController = RxJavaCrashCourseApplication.getFrontController();
        presenter = new ForecastPresenter(view, interactor, frontController);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_forecast, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_set_forecast_city:
                presenter.onActionSetForecastCityClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public IToolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setForecastRecyclerAdapter(ForecastAdapter adapter) {
        if (bRecyclerView.getAdapter() == null) {
            bRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            bRecyclerView.setAdapter(adapter);
        } else {
            bRecyclerView.swapAdapter(adapter, true);
        }
    }

    @Override
    public void setForecastLocation(String location) {
        bLocationTextView.setText(getString(R.string.displaying_forecast_for_location, location));
    }

    @Override
    public void displayLoadingDialog(int loadingMessageStringResId) {
        loadingDialog = LoadingDialog.newInstance(getContext(), loadingMessageStringResId);
        loadingDialog.setCancelable(false);
        loadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void displaySetForecastCityDialog(String city) {
        ForecastDialog.newInstance(city)
                .setOnDialogConfirmationButtonClickedListener(new ForecastDialog.OnForecastDialogConfirmationButtonClickedListener() {
                    @Override
                    public void onConfirmationButtonClicked(String city) {
                        presenter.onForecastCitySet(city);
                    }
                })
                .show(getChildFragmentManager(), ForecastDialog.TAG);
    }

    @Override
    public void displaySnackbar(int stringResId, int duration) {
        final View view = getView();
        if (view != null) {
            Snackbar.make(view, stringResId, duration);
        }
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed(getActivity());
    }
}
