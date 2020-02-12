package com.manoj.weatherapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.manoj.weatherapp.R;
import com.manoj.weatherapp.apiconnector.response.CurrentWeather;
import com.manoj.weatherapp.databinding.ActivityMainBinding;
import com.manoj.weatherapp.ui.adapter.ForcastAdapter;
import com.manoj.weatherapp.utils.Utils;
import com.manoj.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;

    private WeatherViewModel mWeatherViewModel;

    private BroadcastReceiver mNetworkConnector = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Utils.isNetworkAvailable(MainActivity.this)) {
                fetchWeatherDetails("Bangalore", false);
            } else {
                errorOccur();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        wait(true);
        fetchWeatherDetails("Bangalore", false);

        mMainBinding.errorLayout.btnRetry.setOnClickListener(v -> {
            wait(true);
            fetchWeatherDetails("Bangalore", false);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mNetworkConnector, intentFilter);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNetworkConnector);
        super.onDestroy();
    }

    private void wait(boolean isloading) {
        if (isloading) {
            mMainBinding.loaderLayout.llRoot.setVisibility(View.VISIBLE);
            mMainBinding.layout.rlRoot.setVisibility(View.GONE);
            mMainBinding.errorLayout.llRoot.setVisibility(View.GONE);
        } else {
            mMainBinding.loaderLayout.llRoot.setVisibility(View.GONE);
            mMainBinding.layout.rlRoot.setVisibility(View.VISIBLE);
            mMainBinding.errorLayout.llRoot.setVisibility(View.GONE);
        }
    }

    private void fetchWeatherDetails(String cityName, boolean isSearching) {
        mWeatherViewModel.getCurrentWeatherLiveData(cityName).observeForever(result -> {
            if (result != null) {
                wait(false);
                mMainBinding.layout.txtCityName.setText(result.getLocation().getName());
                mMainBinding.layout.txtTemperature.setText(result.getCurrent().getTemperature() + "\u00B0");

                List<CurrentWeather.Current> currents = new ArrayList<>();
                for (int i = 1; i <= 7; i++) {
                    CurrentWeather.Current current = result.getCurrent();
                    current.setObservationTime("Day");
                    currents.add(current);
                }
                setForcastData(currents);
            } else {
                wait(false);
                errorOccur();
            }
        });
    }

    private void errorOccur() {
        mMainBinding.errorLayout.llRoot.setVisibility(View.VISIBLE);
        mMainBinding.loaderLayout.llRoot.setVisibility(View.GONE);
        mMainBinding.layout.rlRoot.setVisibility(View.GONE);
    }

    private void setForcastData(List<CurrentWeather.Current> list) {
        mMainBinding.layout.recycleWeeklyWeather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        mMainBinding.layout.recycleWeeklyWeather.setAdapter(new ForcastAdapter(list));
    }
}
