package com.example.weather.ui.place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.logic.network.ResponseBean;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<ResponseBean> placeList;

    public PlaceAdapter(List<ResponseBean> placeList){
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResponseBean place = placeList.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAddress.setText(place.getName());
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
