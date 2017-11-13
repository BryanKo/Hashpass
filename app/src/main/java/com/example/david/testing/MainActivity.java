package com.example.david.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.johnhiott.darkskyandroidlib.ForecastApi;
import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ForecastApi.create("4fb2c715ea744173c72290437de1c776");
        setContentView(R.layout.activity_main);

        Button currentFood = (Button) findViewById(R.id.button13);
        Button currentBar = (Button) findViewById(R.id.button14);
        Button currentActive = (Button) findViewById(R.id.button15);
        Button currentIndoor = (Button) findViewById(R.id.button16);

        final TextView weather_print = (TextView) findViewById(R.id.textView_Weather);

        final RequestBuilder weather = new RequestBuilder();

        Request request = new Request();
        request.setLat("36.9741");
        request.setLng("122.0308");
        request.setUnits(Request.Units.US);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);


        weather.getWeather(request, new Callback<WeatherResponse>() {
            String TAG = null;

            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                int hello =
                        Log.w("JSON print", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                weather_print.setText("Weather:" + hello);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                weather_print.setText("absolute failure");
            }
        });

        currentFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, foodCurrent.class);
                startActivity(intent);
            }
        });
        currentBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, barCurrent.class);
                startActivity(intent);
            }
        });
        currentActive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, activeCurrent.class);
                startActivity(intent);
            }
        });
        currentIndoor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, indoorCurrent.class);
                startActivity(intent);
            }
        });

    }
}
