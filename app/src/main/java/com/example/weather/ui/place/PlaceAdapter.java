package com.example.weather.ui.place;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.logic.model.Place;
import com.example.weather.ui.weather.WeatherActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<Place> placeList;

    public PlaceAdapter(List<Place> placeList){
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(view1 -> {
            int position = holder.getAdapterPosition();
            Place place = placeList.get(position);
            String lng = place.location.lng;
            String lat = place.location.lat;
            String name = place.name;
            WeatherActivity.actionStart(parent.getContext(),lng,lat,name);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeName.setText(place.name);
        holder.placeAddress.setText(place.address);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placeAddress;
        public ViewHolder(View view){
            super(view);
            placeName = view.findViewById(R.id.placeName);
            placeAddress = view.findViewById(R.id.placeAddress);
        }

    }
}
