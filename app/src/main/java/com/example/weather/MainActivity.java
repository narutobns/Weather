package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weather.logic.network.HttpCallback;
import com.example.weather.logic.network.ResponseBean;
import com.example.weather.logic.network.WeatherNetwork;
import com.example.weather.ui.place.PlaceAdapter;
import com.example.weather.ui.place.PlaceFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpCallback, View.OnClickListener {
    private String url = "http://guolin.tech/api/china";
    //private String response;
    private String requestMethod ="GET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container,new PlaceFragment()).commit();
        EditText editText = findViewById(R.id.searchPlaceEdit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String content = charSequence.toString();
                if (content != null) {
                    WeatherNetwork weatherNetwork = new WeatherNetwork(MainActivity.this, url, requestMethod);
                    weatherNetwork.execute();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onFinish(List<ResponseBean> response) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PlaceAdapter adapter = new PlaceAdapter(response);
        recyclerView.setAdapter(adapter);
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