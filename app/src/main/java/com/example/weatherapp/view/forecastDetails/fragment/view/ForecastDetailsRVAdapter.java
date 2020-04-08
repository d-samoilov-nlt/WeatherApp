package com.example.weatherapp.view.forecastDetails.fragment.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.forecast.fullForecast.IOneTimeForecastDisplayModel;

import java.util.List;

public class ForecastDetailsRVAdapter extends RecyclerView.Adapter<ForecastDetailsRVAdapter.ForecastDetailsViewHolder> {
    private List<IOneTimeForecastDisplayModel> forecastDisplayModels;
    private Resources resources;
    private Context context;

    public ForecastDetailsRVAdapter(List<IOneTimeForecastDisplayModel> forecastDisplayModels) {
        this.forecastDisplayModels = forecastDisplayModels;
    }

    void updateForecastList(List<IOneTimeForecastDisplayModel> forecastDisplayModels) {
        this.forecastDisplayModels.clear();
        this.forecastDisplayModels.addAll(forecastDisplayModels);
    }

    @NonNull
    @Override
    public ForecastDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_one_time_forecast, parent, false);

        resources = view.getResources();
        context = view.getContext();

        return new ForecastDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastDetailsViewHolder holder, int position) {
        IOneTimeForecastDisplayModel dm = forecastDisplayModels.get(position);

        holder.tvTime.setText(dm.getTime());
        holder.tvTemp.setText(dm.getTemp());
        holder.tvHumidity.setText(dm.getHumidity());
        holder.tvPressure.setText(dm.getPressure());
        holder.tvWind.setText(dm.getWind());

        Glide
                .with(context)
                .load(dm.getWeatherImageUrl())
                .centerCrop()
                .into(holder.ivWeatherView);

        updateContainerBackground(holder.llItemContainer, position);

    }

    private void updateContainerBackground(LinearLayout container, int position) {
        boolean isEvenPosition = position % 2 == 0;

        if (isEvenPosition) {
            container.setBackgroundColor(
                    resources
                            .getColor(R.color.colorEvenOneTimeForecastBackground));
        } else {
            container.setBackgroundColor(
                    resources
                            .getColor(R.color.colorOneTimeForecastBackground));
        }
    }

    @Override
    public int getItemCount() {
        return forecastDisplayModels.size();
    }

    static class ForecastDetailsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItemContainer;
        TextView tvTime;
        ImageView ivWeatherView;
        TextView tvTemp;
        TextView tvPressure;
        TextView tvWind;
        TextView tvHumidity;


        ForecastDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            llItemContainer = itemView.findViewById(R.id.ll_one_time_forecast_container);
            tvTime = itemView.findViewById(R.id.tv_one_time_forecast_time);
            ivWeatherView = itemView.findViewById(R.id.iv_one_time_forecast_icon);
            tvTemp = itemView.findViewById(R.id.tv_one_time_forecast_temp);
            tvPressure = itemView.findViewById(R.id.tv_one_time_forecast_pressure);
            tvWind = itemView.findViewById(R.id.tv_one_time_forecast_wind);
            tvHumidity = itemView.findViewById(R.id.tv_one_time_forecast_humidity);
        }
    }
}
