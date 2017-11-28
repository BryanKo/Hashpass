package com.example.david.testing;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//<<<<<<< HEAD
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//=======
//>>>>>>> 996bf80849baf035cfab23da831a8e9b3c0e2b1d
import com.johnhiott.darkskyandroidlib.ForecastApi;
import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout rLayout;
//<<<<<<< HEAD
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
 //   private ArrayList<String> mDataSet;
    private ArrayList<DataModel> allDataArray;
//=======
//>>>>>>> 996bf80849baf035cfab23da831a8e9b3c0e2b1d

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        double axx= weatherCurrent.ax;
        double ayy = weatherCurrent.ay;
        super.onCreate(savedInstanceState);
        ForecastApi.create("4fb2c715ea744173c72290437de1c776");
        setContentView(R.layout.activity_main);
      //  rLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.afternoon));
        //Get Calendar and Date to display NOW and the next 5 hours;
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("hh a");
        Date date = new Date();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        Drawable morn = (Drawable)getResources().getDrawable(R.drawable.amorning);
        Drawable after = (Drawable)getResources().getDrawable(R.drawable.afternoon);
        if(timeOfDay >= 0 && timeOfDay < 12){
            Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
        //    view.setBackground(morn);
        }
        else if(timeOfDay >= 12 && timeOfDay < 16){
            Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();

        }
        else if(timeOfDay >= 16 && timeOfDay < 21){
            Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
        }
        else if(timeOfDay >= 21 && timeOfDay < 24){
            Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
        }

 //       Button currentFood = (Button) findViewById(R.id.foodButton);
        //Recycler view*************************************************************************8
        allDataArray = new ArrayList<>();
       // mButtonSet = new ArrayList<>();

//        for (int i = 0; i < 5; i++) {
//            mDataSet.add (calendar.get(Calendar.HOUR+i)+""+calendar.get(Calendar.AM_PM));
//        }
//        allDataArray.add (new DataModel("Now", "Food"));
//        calendar.add(Calendar.HOUR, 1);
//        Date oneHour = calendar.getTime();
//        allDataArray.add (new DataModel(dateFormat.format(oneHour), "Food2"));
//        calendar.add(Calendar.HOUR, 1);
//        Date twoHour = calendar.getTime();
//        allDataArray.add ( new DataModel(dateFormat.format(twoHour), "Food3"));
//        calendar.add(Calendar.HOUR, 1);
//        Date threeHour = calendar.getTime();
//        mDataSet.add (dateFormat.format(threeHour));
//        calendar.add(Calendar.HOUR, 1);
//        Date fourHour = calendar.getTime();
//        mDataSet.add (dateFormat.format(fourHour));
//        calendar.add(Calendar.HOUR, 1);
//        Date fiveHour = calendar.getTime();
//        mDataSet.add (dateFormat.format(fiveHour));
//        int i = 0;
//
//        while (i<5){
//            mDataSet.add (calendar.get(Calendar.HOUR)+""+calendar.get(Calendar.AM_PM));
//            i++;
//        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mAdapter = new MainAdapter(allDataArray);
        mAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mAdapter);

        //Recycler view*************************************************************************8



//        Button currentBar = (Button) findViewById(R.id.button14);
//        Button currentActive = (Button) findViewById(R.id.button15);
//        Button currentIndoor = (Button) findViewById(R.id.button16);

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
               // weather_print.setText("Weather in Santa Cruz is " + (tempHourly) + " °F.");
                weather_print.setText((tempHourly) + " °F");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                Log.d(TAG, retrofitError.toString());
                weather_print.setText("failure to access weather");
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


