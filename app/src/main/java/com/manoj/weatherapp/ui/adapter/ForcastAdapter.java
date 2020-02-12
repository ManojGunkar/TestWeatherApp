package com.manoj.weatherapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manoj.weatherapp.R;
import com.manoj.weatherapp.apiconnector.response.CurrentWeather;
import com.manoj.weatherapp.databinding.ItemWeeklyBinding;

import java.util.List;

public class ForcastAdapter extends RecyclerView.Adapter<ForcastAdapter.ViewHolder> {

    private List<CurrentWeather.Current> list;

    public ForcastAdapter(List<CurrentWeather.Current> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWeeklyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_weekly, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemWeeklyBinding binding;

        public ViewHolder(@NonNull ItemWeeklyBinding weeklyBinding) {
            super(weeklyBinding.getRoot());
            this.binding = weeklyBinding;
        }

        private void bindTo(CurrentWeather.Current current) {
            binding.txtDayWeekly.setText(current.getObservationTime());
            binding.txtTempForcast.setText(current.getTemperature() + "\u00B0" + " / " + current.getTemperature() + "\u00B0");
            binding.txtWeatherForcast.setText(current.getWeatherDescriptions().get(0));
            Glide.with(binding.getRoot().getContext())
                    .load(current.getWeatherIcons().get(0))
                    .into(binding.imgWeekly);
        }
    }
}
