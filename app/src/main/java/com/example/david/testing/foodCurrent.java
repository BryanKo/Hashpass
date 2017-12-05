package com.example.david.testing;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;

public class foodCurrent extends AppCompatActivity {
    YelpFusionApiFactory apiFactory;
    ListView businessList;
    String appId = "3v_MqsnS4xUByPuMTTjKZw";
    String appSecret = "41AchC7qNowuPe2y2GPUnGPj4Xc25h9SRCEyuSzU7QYZKq6gzfgTUyyHpu69PohB";
    ArrayList<Business> businesses;
    ArrayList<Business> allBusinesses = new ArrayList<>();
    final ArrayList<String> businessesImg = new ArrayList<>();
    final ArrayList<String> businessesName = new ArrayList<>();
    final ArrayList<String> businesessLoc = new ArrayList<>();
    final ArrayList<Double> businesessDist = new ArrayList<>();

    final ArrayList<String> businesessPrice = new ArrayList<>();
    final ArrayList<Double> businesessRating = new ArrayList<>();
    final ArrayList<Integer> businesessReviewCnt = new ArrayList<>();
    int businessIndex = 0;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // StrictMode allows the api to load on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses_current);

        businessList = (ListView) findViewById(R.id.lvBusinesses);

        Bundle extras = getIntent().getExtras();
        String[] currWeather = extras.getStringArray("passCurrWeather");
        double[] currTemp = extras.getDoubleArray("passCurrTemp");
        double currLat = extras.getDouble("passCurrLat");
        double currLng = extras.getDouble("passCurrLng");
        double currTime = extras.getDouble("passCurrTime");

        apiFactory = new YelpFusionApiFactory();
        if (currTemp[0] > 30 && (currWeather[0].equalsIgnoreCase("clear") ||
               currWeather[0].equalsIgnoreCase("rain") ||
               currWeather[0].equalsIgnoreCase("fog") ||
               currWeather[0].equalsIgnoreCase("cloudy") ||
               currWeather[0].equalsIgnoreCase("wind") ||
               currWeather[0].equalsIgnoreCase("partly cloudy")
        )) {
            try {
                // Api call with client id and client secret id
                YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

                // Hashmap to store parameters
                Map<String, String> params = new HashMap<>();
                params.put("term", "restaurants");
                params.put("radius", "16000");              // 16000 meters = 10 mile radius; radius is calculated in meters
                params.put("latitude", String.valueOf(currLat));
                params.put("longitude", String.valueOf(currLng));
                //params.put("open_now", "false");            // false for now b/c it'll crash if nothing is found (will remove when open_at is implemented
                params.put("open_at", String.valueOf(Double.valueOf(currTime).longValue()));
                params.put("limit", "50");

                Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
                SearchResponse searchResponse = call.execute().body();

                businesses = searchResponse.getBusinesses();

                Business business = businesses.get(businessIndex);
                allBusinesses = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                // Api call with client id and client secret id
                YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

                // Hashmap to store parameters
                Map<String, String> params = new HashMap<>();
                params.put("term", "fast food");
                params.put("radius", "16000");              // 16000 meters = 10 mile radius; radius is calculated in meters
                params.put("latitude", String.valueOf(currLat));
                params.put("longitude", String.valueOf(currLng));
                params.put("open_now", "false");            // false for now b/c it'll crash if nothing is found
                //params.put("open_at", String.valueOf(Double.valueOf(currTime).longValue()));
                params.put("limit", "50");

                Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
                SearchResponse searchResponse = call.execute().body();

                businesses = searchResponse.getBusinesses();

                Business business = businesses.get(businessIndex);
                allBusinesses = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = businessIndex; i < businesses.size(); i++) {
            allBusinesses.add(businesses.get(i));
            businessIndex++;
        }

        for (int i=0; i < 5; i++) {
            int low = 0;
            int high = allBusinesses.size();
            int randNum = r.nextInt(high-low) + low;
            businessesImg.add(allBusinesses.get(randNum).getImageUrl());
            businessesName.add(allBusinesses.get(randNum).getName());
            businesessLoc.add((allBusinesses.get(randNum).getLocation().getAddress1() + ", " +
                               allBusinesses.get(randNum).getLocation().getCity() + ", " +
                               allBusinesses.get(randNum).getLocation().getState() + " " +
                               allBusinesses.get(randNum).getLocation().getZipCode()));
            businesessDist.add(allBusinesses.get(randNum).getDistance());

            businesessPrice.add(allBusinesses.get(randNum).getPrice());
            businesessRating.add(allBusinesses.get(randNum).getRating());
            businesessReviewCnt.add(allBusinesses.get(randNum).getReviewCount());
        }

        businessListView adapter = new businessListView(this, businessesImg, businessesName, businesessLoc, businesessDist);
        businessList.setAdapter(adapter);

        businessList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(businessesName != null) {
                    //Toast.makeText(getApplicationContext(), businessesName.get(+position), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(foodCurrent.this, businessInfo.class);
                    Bundle extras = new Bundle();
                    extras.putString("passBusinessImg", businessesImg.get(+position));
                    extras.putString("passBusinessName", businessesName.get(+position));
                    extras.putString("passBusinessLoc", businesessLoc.get(+position));
                    extras.putDouble("passBusinessDist", businesessDist.get(+position));

                    extras.putString("passBusinessPrice", businesessPrice.get(+position));
                    extras.putDouble("passBusinessRating", businesessRating.get(+position));
                    extras.putInt("passBusinessReviewCnt", businesessReviewCnt.get(+position));
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }
}
