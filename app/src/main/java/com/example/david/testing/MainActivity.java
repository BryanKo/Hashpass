package com.example.david.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> mDataSet;
 //   private ArrayList<Button> mButtonSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ForecastApi.create("4fb2c715ea744173c72290437de1c776");
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("hh a");
        Date date = new Date();

 //       Button currentFood = (Button) findViewById(R.id.foodButton);
        //Recycler view*************************************************************************8
        mDataSet = new ArrayList<>();
   //     mButtonSet = new ArrayList<>();

//        for (int i = 0; i < 5; i++) {
//            mDataSet.add (calendar.get(Calendar.HOUR+i)+""+calendar.get(Calendar.AM_PM));
//        }
        mDataSet.add ("NOW");
        calendar.add(Calendar.HOUR, 1);
        Date oneHour = calendar.getTime();
        mDataSet.add (dateFormat.format(oneHour));
        calendar.add(Calendar.HOUR, 1);
        Date twoHour = calendar.getTime();
        mDataSet.add (dateFormat.format(twoHour));
        calendar.add(Calendar.HOUR, 1);
        Date threeHour = calendar.getTime();
        mDataSet.add (dateFormat.format(threeHour));
        calendar.add(Calendar.HOUR, 1);
        Date fourHour = calendar.getTime();
        mDataSet.add (dateFormat.format(fourHour));
        calendar.add(Calendar.HOUR, 1);
        Date fiveHour = calendar.getTime();
        mDataSet.add (dateFormat.format(fiveHour));
//        int i = 0;
//
//        while (i<5){
//            mDataSet.add (calendar.get(Calendar.HOUR)+""+calendar.get(Calendar.AM_PM));
//            i++;
//        }
//        mButtonSet.add (currentFood);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);

        //Recycler view*************************************************************************8



//        Button currentBar = (Button) findViewById(R.id.button14);
//        Button currentActive = (Button) findViewById(R.id.button15);
//        Button currentIndoor = (Button) findViewById(R.id.button16);

        final TextView weather_print = (TextView) findViewById(R.id.textView_Weather);

        RequestBuilder weather = new RequestBuilder();

        Request request = new Request();
        request.setLat("36.9914");
        request.setLng("-122.0609");
        request.setUnits(Request.Units.US);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);

        weather.getWeather(request, new Callback<WeatherResponse>() {
            String TAG = null;
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                double celsiusTemp = Log.v(TAG, "First Temp: " + weatherResponse.getCurrently().getTemperature());
                double farenTemp = (celsiusTemp * (9/5)) + 32;
                double tempSummary = Log.v(TAG, "Summary: " + weatherResponse.getCurrently().getSummary());
                double tempHourly = Log.v(TAG, "Hourly Sum: " + weatherResponse.getHourly().getSummary());
                weather_print.setText("Weather in Santa Cruz is " + (farenTemp) + " °F.");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                Log.d(TAG, retrofitError.toString());
            }
        });

//        currentFood.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, foodCurrent.class);
//                startActivity(intent);
//            }
//        });
//        currentBar.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, barCurrent.class);
//                startActivity(intent);
//            }
//        });
//        currentActive.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, activeCurrent.class);
//                startActivity(intent);
//            }
//        });
//        currentIndoor.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, indoorCurrent.class);
//                startActivity(intent);
//            }
//        });

    }
}
