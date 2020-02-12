package com.manoj.weatherapp.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
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

        mMainBinding.layout.txtChangeCity.setOnClickListener(v -> searchCity());
        mMainBinding.txtError.setOnClickListener(v -> {
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
            mMainBinding.progressBar.setVisibility(View.VISIBLE);
            mMainBinding.layout.rlRoot.setVisibility(View.GONE);
            mMainBinding.txtError.setVisibility(View.GONE);
        } else {
            mMainBinding.progressBar.setVisibility(View.GONE);
            mMainBinding.layout.rlRoot.setVisibility(View.VISIBLE);
            mMainBinding.txtError.setVisibility(View.GONE);
        }
    }

    private void fetchWeatherDetails(String cityName, boolean isSearching) {
        mWeatherViewModel.getCurrentWeatherLiveData(cityName).observeForever(result -> {
            if (result != null) {
                wait(false);
                mMainBinding.layout.txtCityName.setText(result.getLocation().getName());
                mMainBinding.layout.txtTime.setText(result.getLocation().getLocaltime());
                mMainBinding.layout.txtTemperature.setText(result.getCurrent().getTemperature() + "\u2103");
                mMainBinding.layout.txtFeelLike.setText(result.getCurrent().getFeelslike() + "\u2103");
                mMainBinding.layout.txtHumidity.setText(String.valueOf(result.getCurrent().getHumidity()));
                mMainBinding.layout.txtPrecipitation.setText(result.getCurrent().getPrecip() + "%");
                mMainBinding.layout.txtWeatherDesc.setText(result.getCurrent().getWeatherDescriptions().get(0));
                Glide.with(this)
                        .load(result.getCurrent().getWeatherIcons().get(0))
                        .into(mMainBinding.layout.imgWeatherIc);

                List<CurrentWeather.Current> currents = new ArrayList<>();
                for (int i = 1; i <= 7; i++) {
                    CurrentWeather.Current current = result.getCurrent();
                    current.setObservationTime("Day");
                    currents.add(current);
                }
                setForcastData(currents);
            } else {
                if (isSearching) {
                    wait(false);
                    errorOccur();
                } else {
                    wait(false);
                    errorOccur();
                    mMainBinding.txtError.setText(R.string.server_error);
                }
            }
        });
    }

    private void errorOccur() {
        mMainBinding.txtError.setVisibility(View.VISIBLE);
        mMainBinding.progressBar.setVisibility(View.GONE);
        mMainBinding.layout.rlRoot.setVisibility(View.GONE);
    }

    private void setForcastData(List<CurrentWeather.Current> list) {
        mMainBinding.layout.recycleWeeklyWeather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mMainBinding.layout.recycleWeeklyWeather.setAdapter(new ForcastAdapter(list));
    }

    private void searchCity() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View changeCityView = factory.inflate(R.layout.layout_dialog, null);
        final AlertDialog changeCityDialog = new AlertDialog.Builder(this).create();
        changeCityDialog.setView(changeCityView);

        EditText editText = changeCityView.findViewById(R.id.et_city);

        changeCityView.findViewById(R.id.txt_ok).setOnClickListener(v -> {
            String cityName = editText.getText().toString();
            if (TextUtils.isEmpty(cityName)) {
                Toast.makeText(this, R.string.valid_city, Toast.LENGTH_SHORT).show();
            } else {
                wait(true);
                fetchWeatherDetails(cityName, true);
                changeCityDialog.dismiss();
            }
        });
        changeCityView.findViewById(R.id.txt_cancel).setOnClickListener(v -> changeCityDialog.dismiss());
        changeCityDialog.show();

    }
}
