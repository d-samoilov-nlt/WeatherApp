package com.example.weatherapp.locationList.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationUtilsType;
import com.example.weatherapp.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public class FavoriteLocationListRVAdapter extends RecyclerView.Adapter<FavoriteLocationListRVAdapter.LocationItemViewHolder> {
    private final OnFavoriteLocationItemClickListener onFavoriteLocationItemClickListener;
    private List<IFavoriteLocationItemDisplayModel> displayModels;
    private Resources resources;
    private Context context;

    public FavoriteLocationListRVAdapter(OnFavoriteLocationItemClickListener onFavoriteLocationItemClickListener,
                                         List<IFavoriteLocationItemDisplayModel> displayModels) {
        this.onFavoriteLocationItemClickListener = onFavoriteLocationItemClickListener;
        this.displayModels = displayModels;
    }

    public void updateForecastList(List<IFavoriteLocationItemDisplayModel> displayModels) {
        this.displayModels.clear();
        this.displayModels.addAll(displayModels);
    }

    @NonNull
    @Override
    public FavoriteLocationListRVAdapter.LocationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location_list, parent, false);

        resources = view.getResources();
        context = view.getContext();

        return new FavoriteLocationListRVAdapter.LocationItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteLocationListRVAdapter.LocationItemViewHolder holder, int position) {
        IFavoriteLocationItemDisplayModel dm = displayModels.get(position);

        int utilsType = dm.getShortDetailsDisplayModel().getUtilsType();

        if (utilsType == FavoriteLocationUtilsType.CELSIUS.getValue()) {
            holder.tvSortDetails.setText(
                    String.format(
                            resources.getString(R.string.favorite_location_short_data_cel),
                            dm.getShortDetailsDisplayModel().getTemp(),
                            dm.getShortDetailsDisplayModel().getForecast()));

        } else if (utilsType == FavoriteLocationUtilsType.FAHRENHEIT.getValue()) {
            holder.tvSortDetails.setText(
                    String.format(
                            resources.getString(R.string.favorite_location_short_data_far),
                            dm.getShortDetailsDisplayModel().getTemp(),
                            dm.getShortDetailsDisplayModel().getForecast()));
        } else {
            throw new IllegalArgumentException("Unsupported FavoriteLocationUtilsType - " + utilsType);
        }

        holder.tvCityName.setText(dm.getShortDetailsDisplayModel().getCityName());

        Glide
                .with(context)
                .load(dm.getIconUrl())
                .centerCrop()
                .into(holder.ivWeatherView);

        holder.clItemContainer.setOnClickListener(v -> onFavoriteLocationItemClickListener.onClick(dm));
    }

    @Override
    public int getItemCount() {
        return displayModels.size();
    }

    static class LocationItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clItemContainer;
        TextView tvSortDetails;
        ImageView ivWeatherView;
        TextView tvCityName;

        LocationItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSortDetails = itemView.findViewById(R.id.tv_item_location_short_details);
            ivWeatherView = itemView.findViewById(R.id.iv_item_location_icon);
            tvCityName = itemView.findViewById(R.id.tv_item_location_city_name);
            clItemContainer = itemView.findViewById(R.id.cl_item_location_container);
        }
    }

    public interface OnFavoriteLocationItemClickListener {
        void onClick(IFavoriteLocationItemDisplayModel dm);
    }
}
