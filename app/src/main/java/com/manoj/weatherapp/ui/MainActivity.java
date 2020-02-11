package com.manoj.weatherapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.manoj.weatherapp.R;
import com.manoj.weatherapp.databinding.ActivityMainBinding;
import com.manoj.weatherapp.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;

    private WeatherViewModel mWeatherViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        wait(true);
        fetchWeatherDetails("Bangalore");

    }

    private void wait(boolean isloading) {
        if (isloading) {
            mMainBinding.progressBar.setVisibility(View.VISIBLE);
            mMainBinding.layout.rlRoot.setVisibility(View.GONE);
        } else {
            mMainBinding.progressBar.setVisibility(View.GONE);
            mMainBinding.layout.rlRoot.setVisibility(View.VISIBLE);
        }
    }

    private void fetchWeatherDetails(String cityName) {
        mWeatherViewModel.getCurrentWeatherLiveData(cityName).observe(this, result -> {
            if (result != null) {
                wait(false);
                mMainBinding.layout.txtCityName.setText(result.getLocation().getName());
                mMainBinding.layout.txtTime.setText(result.getLocation().getLocaltime());
                mMainBinding.layout.txtTemperature.setText(result.getCurrent().getTemperature() + "\u2103");
                mMainBinding.layout.txtFeelLike.setText(result.getCurrent().getFeelslike() + "\u2103");
                mMainBinding.layout.txtHumidity.setText(String.valueOf(result.getCurrent().getHumidity()));
                mMainBinding.layout.txtPrecipitation.setText(result.getCurrent().getPrecip() + "%");
                Glide.with(this)
                        .load(result.getCurrent().getWeatherIcons().get(0))
                        .into(mMainBinding.layout.imgWeatherIc);
            } else {
                //todo: handle error
            }
        });
    }

}
