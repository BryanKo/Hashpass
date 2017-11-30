package com.example.david.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.johnhiott.darkskyandroidlib.ForecastApi;
import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class MainActivity extends AppCompatActivity {

    //new layout recyclerview
    private RelativeLayout rLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<DataModel> allDataArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //double axx= weatherCurrent.ax;
        //double ayy = weatherCurrent.ay;
        final double axx = 36.9741;
        final double ayy = -122.0308;
        final String[] currWeather = new String[1];
        final double[] currTemp = new double[1];
        final String[] currIcon = new String[1];
        super.onCreate(savedInstanceState);
        ForecastApi.create("4fb2c715ea744173c72290437de1c776");
        final String apiKey = "4fb2c715ea744173c72290437de1c776";
        setContentView(R.layout.activity_main);

        //Calendar stuff
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("hh a");
        Date date = new Date();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

//        Button currentFood = (Button) findViewById(R.id.button13);
//        Button currentBar = (Button) findViewById(R.id.button14);
//        Button currentActive = (Button) findViewById(R.id.button15);
//        Button currentIndoor = (Button) findViewById(R.id.button16);

        final TextView weather_print = (TextView) findViewById(R.id.textView_Weather);

        final RequestBuilder weather = new RequestBuilder();

        final String Latitude = Double.toString(axx);
        final String Longitude = Double.toString(ayy);
        final String baseURL = "https://api.darksky.net/forecast";

        Request request = new Request();
        request.setLat(Latitude);
        request.setLng(Longitude);
        request.setUnits(Request.Units.US);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = baseURL +"/" +apiKey + "/" + Latitude + "," + Longitude;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject object = new JSONObject(response);
                            //Log.d("json object", object.toString());

                            JSONObject current = object.getJSONObject("currently");
                            //Log.d("Current Information",current.toString());

                            int temp = current.getInt("temperature");
                            currTemp[0] = temp;
                            Log.d("Temperature:", " " + temp);

                            if (temp < 50) {
                                System.out.println("1");
                            }
                            else if (temp > 55) {
                                System.out.println("2");
                            }
                            else {
                                System.out.println("error");
                            }

                            String summary = current.getString("summary");
                            currWeather[0] = summary;
                            Log.d("Summary",summary);

                            if (summary.equals("Clear")) {
                                System.out.println("Temperature is currently clear");
                            }
                            else {
                                System.out.println("Temperature is not clear");
                            }

                            String icon = current.getString("icon");
                            currIcon[0] = icon;
                            Log.d("Icon",icon);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("WEB_RESPONSE", response.toString());
                        System.out.println("Successfully and is working");
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Not Working");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        weather.getWeather(request, new Callback<WeatherResponse>() {
            String TAG = null;
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                Log.d(TAG, "First Temp: " + weatherResponse.getCurrently().getTemperature());
                Log.d(TAG, "Summary: " + weatherResponse.getCurrently().getSummary());
                Log.d(TAG, "Hourly Sum: " + weatherResponse.getHourly().getSummary());
                weather_print.setText(weatherResponse.getCurrently().getTemperature() + " °F.");
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
//                Bundle extras = new Bundle();
//                extras.putStringArray("passCurrWeather", currWeather);
//                extras.putDoubleArray("passCurrTemp", currTemp);
//                extras.putDouble("passCurrLat", axx);
//                extras.putDouble("passCurrLng", ayy);
//                intent.putExtras(extras);
//                startActivity(intent);
//            }
//        });
//        currentBar.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, barCurrent.class);
//                Bundle extras = new Bundle();
//                extras.putStringArray("passCurrWeather", currWeather);
//                extras.putDoubleArray("passCurrTemp", currTemp);
//                extras.putDouble("passCurrLat", axx);
//                extras.putDouble("passCurrLng", ayy);
//                intent.putExtras(extras);
//                startActivity(intent);
//            }
//        });
//        currentActive.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, activeCurrent.class);
//                Bundle extras = new Bundle();
//                extras.putStringArray("passCurrWeather", currWeather);
//                extras.putDoubleArray("passCurrTemp", currTemp);
//                extras.putDouble("passCurrLat", axx);
//                extras.putDouble("passCurrLng", ayy);
//                intent.putExtras(extras);
//                startActivity(intent);
//            }
//        });
//        currentIndoor.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(MainActivity.this, indoorCurrent.class);
//                Bundle extras = new Bundle();
//                extras.putStringArray("passCurrWeather", currWeather);
//                extras.putDoubleArray("passCurrTemp", currTemp);
//                extras.putDouble("passCurrLat", axx);
//                extras.putDouble("passCurrLng", ayy);
//                intent.putExtras(extras);
//                startActivity(intent);
//            }
//        });

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
        //mAdapter = new com.example.david.testing.MainAdapter(allDataArray);
        mAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }
}


