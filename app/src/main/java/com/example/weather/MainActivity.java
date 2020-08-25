package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.logic.model.Place;
import com.example.weather.logic.network.HttpCallback;
import com.example.weather.logic.network.WeatherNetwork;
import com.example.weather.ui.place.PlaceAdapter;
import com.example.weather.ui.place.PlaceFragment;
import com.example.weather.ui.place.PlaceViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpCallback, View.OnClickListener {
    private final String Token = "gTp7dBJS3cGkAxF1";
    private String url = "https://api.caiyunapp.com/v2/place?query=";
    private String requestMethod ="GET";
    private RecyclerView recyclerView;
    private ImageView bgImageView;
    private PlaceViewModel placeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        EditText editText = findViewById(R.id.searchPlaceEdit);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        bgImageView = findViewById(R.id.bgImageView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (!content.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    String newUrl = url + content + "&token=gTp7dBJS3cGkAxF1&lang=zh_CN";
                    WeatherNetwork weatherNetwork = new WeatherNetwork(MainActivity.this, newUrl, requestMethod);
                    weatherNetwork.execute();
                } else {
                    recyclerView.setVisibility(View.GONE);
                    bgImageView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onFinish(String... response) {
        placeViewModel.setResponse(response[0]);
        PlaceAdapter adapter = new PlaceAdapter(placeViewModel.getResponse().getValue());
        recyclerView.setAdapter(adapter);
//        placeViewModel.getResponse().observe(this, places -> {
//            adapter.notifyDataSetChanged();
//        });
    }

    @Override
    public void onError(String TAG) {
        Log.e(TAG,"Network Error");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            default:
                break;
        }

    }
}