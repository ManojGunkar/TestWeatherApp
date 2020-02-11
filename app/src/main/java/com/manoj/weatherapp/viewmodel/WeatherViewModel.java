package com.manoj.weatherapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manoj.weatherapp.apiconnector.response.CurrentWeather;
import com.manoj.weatherapp.repository.WeatherRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class WeatherViewModel extends ViewModel {

    private WeatherRepository mWeatherRepository = new WeatherRepository();

    private final CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<CurrentWeather> mWeatherLiveData = new MutableLiveData<>();

    public LiveData<CurrentWeather> getCurrentWeatherLiveData(String city) {
        disposables.add(mWeatherRepository.executeCurrentWeatherApi(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> mWeatherLiveData.setValue(result),
                        throwable ->{
                            mWeatherLiveData.setValue(null);
                            HttpException httpException= (HttpException) throwable;
                            if (httpException.code()==404){
                                //todo: handle the error
                            }
                        }
                ));
        return mWeatherLiveData;
    }
}
