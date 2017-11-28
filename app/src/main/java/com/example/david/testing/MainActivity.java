package com.example.david.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //double axx= weatherCurrent.ax;
        //double ayy = weatherCurrent.ay;
        double axx = 36.9741;
        double ayy = -122.0308;
        final String[] currWeather = new String[1];
        super.onCreate(savedInstanceState);
        ForecastApi.create("4fb2c715ea744173c72290437de1c776");
        final String apiKey = "4fb2c715ea744173c72290437de1c776";
        setContentView(R.layout.activity_main);

        Button currentFood = (Button) findViewById(R.id.button13);
        Button currentBar = (Button) findViewById(R.id.button14);
        Button currentActive = (Button) findViewById(R.id.button15);
        Button currentIndoor = (Button) findViewById(R.id.button16);

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
                weather_print.setText("Weather in Santa Cruz is "
                                        + weatherResponse.getCurrently().getTemperature() + " °F.");
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
                Bundle extras = new Bundle();
                extras.putStringArray("passCurrWeather", currWeather);
                intent.putExtras(extras);
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


