package com.example.david.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        double axx= weatherCurrent.ax;
        double ayy = weatherCurrent.ay;
        super.onCreate(savedInstanceState);
        ForecastApi.create("4fb2c715ea744173c72290437de1c776");
        setContentView(R.layout.activity_main);

        Button currentFood = (Button) findViewById(R.id.button13);
        Button currentBar = (Button) findViewById(R.id.button14);
        Button currentActive = (Button) findViewById(R.id.button15);
        Button currentIndoor = (Button) findViewById(R.id.button16);

        final TextView weather_print = (TextView) findViewById(R.id.textView_Weather);

        RequestBuilder weather = new RequestBuilder();

        String Latitude = Double.toString(axx);
        String Longitude = Double.toString(ayy);


        Request request = new Request();
        request.setLat(Latitude);
        request.setLng(Longitude);
        request.setUnits(Request.Units.US);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);

        weather.getWeather(request, new Callback<WeatherResponse>() {
            String TAG = null;
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                double celsiusTemp = Log.d(TAG, "First Temp: " + weatherResponse.getCurrently().getTemperature());
                double farenTemp = (celsiusTemp * (9/5)) + 32;
                double tempSummary = Log.d(TAG, "Summary: " + weatherResponse.getCurrently().getSummary());
                double tempHourly = Log.d(TAG, "Hourly Sum: " + weatherResponse.getHourly().getSummary());
                weather_print.setText("Weather in Santa Cruz is " + (tempHourly) + " °F.");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                Log.d(TAG, retrofitError.toString());
                weather_print.setText("failure to access weather");
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


