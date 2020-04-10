package com.example.weatherapp.view.forecastDetails.fragment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.IDayForecastDisplayModel;

import java.util.ArrayList;
import java.util.List;

public class ForecastSeveralDaysDetailsRvAdapter extends RecyclerView.Adapter<ForecastSeveralDaysDetailsRvAdapter.ForecastDetailsViewHolder> {
    private List<IDayForecastDisplayModel> forecastDisplayModels;

    public ForecastSeveralDaysDetailsRvAdapter(List<IDayForecastDisplayModel> forecastDisplayModels) {
        this.forecastDisplayModels = forecastDisplayModels;
    }

    void updateForecastList(List<IDayForecastDisplayModel> forecastDisplayModels) {
        this.forecastDisplayModels.clear();
        this.forecastDisplayModels.addAll(forecastDisplayModels);
    }

    @NonNull
    @Override
    public ForecastSeveralDaysDetailsRvAdapter.ForecastDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new
                ForecastSeveralDaysDetailsRvAdapter.ForecastDetailsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_several_days_forecast, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastSeveralDaysDetailsRvAdapter.ForecastDetailsViewHolder holder, int position) {
        IDayForecastDisplayModel dm = forecastDisplayModels.get(position);

        holder.tvTitle.setText(dm.getTitle());
        holder.forecastDetailsRVAdapter.updateForecastList(dm.getForecastList());
        holder.forecastDetailsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return forecastDisplayModels.size();
    }

    static class ForecastDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvOneDayForecast;
        ForecastDetailsRVAdapter forecastDetailsRVAdapter;


        ForecastDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_several_days_forecast_title);
            rvOneDayForecast = itemView.findViewById(R.id.rv_several_days_forecast);

            rvOneDayForecast.setLayoutManager(
                    new LinearLayoutManager(
                            itemView.getContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false));

            forecastDetailsRVAdapter = new ForecastDetailsRVAdapter(new ArrayList<>());
            rvOneDayForecast.setAdapter(forecastDetailsRVAdapter);
        }
    }
}