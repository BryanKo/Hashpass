package com.example.david.testing;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;

public class foodCurrent extends AppCompatActivity {
    YelpFusionApiFactory apiFactory;
    TextView businessWeather;
    ListView businessList;
    String appId = "3v_MqsnS4xUByPuMTTjKZw";
    String appSecret = "41AchC7qNowuPe2y2GPUnGPj4Xc25h9SRCEyuSzU7QYZKq6gzfgTUyyHpu69PohB";
    ArrayList<Business> businesses;
    ArrayList<Business> allBusinesses = new ArrayList<>();
    final ArrayList<String> businessesImg = new ArrayList<>();
    final ArrayList<String> businessesName = new ArrayList<>();
    final ArrayList<String> businesessLoc = new ArrayList<>();
    final ArrayList<Double> businesessDist = new ArrayList<>();
    int businessIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // StrictMode allows the api to load on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_current);

        businessList = (ListView) findViewById(R.id.lvBusinesses);

        Bundle extras = getIntent().getExtras();
        String[] currWeather = extras.getStringArray("passCurrWeather");
        double currLat = extras.getDouble("passCurrLat");
        double currLng = extras.getDouble("passCurrLng");
        //Log.d("latlong", String.valueOf(currLat) + " " + String.valueOf(currLng));

        apiFactory = new YelpFusionApiFactory();
        try {
            // Api call with client id and client secret id
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

            // Hashmap to store parameters
            Map<String, String> params = new HashMap<>();
            params.put("term", "food");
            params.put("radius", "39000");               // 16000 meters = 10 mile radius; radius is calculated in meters
            params.put("latitude", String.valueOf(currLat));
            params.put("longitude", String.valueOf(currLng));
            params.put("open_now", "false");            // false for now b/c it'll crash if nothing is found

            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
            SearchResponse searchResponse = call.execute().body();

            businesses = searchResponse.getBusinesses();

            Business business = businesses.get(businessIndex);
            allBusinesses = new ArrayList<>();

//            businessName.setText(business.getName());
//            businessLoc.setText(business.getLocation().getAddress1() + ", " + business.getLocation().getCity() + ", " + business.getLocation().getState() + " " + business.getLocation().getZipCode());
//            businessRating.setText(String.valueOf(business.getRating()));
//            businessPrice.setText(business.getPrice());
//            businessDist.setText(String.valueOf(round((business.getDistance() / 1609.34), 2)).concat(" miles"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("businessArr", String.valueOf(businesses.size()));

        for (int i = businessIndex; i < businesses.size(); i++) {
            allBusinesses.add(businesses.get(i));
            businessIndex++;
        }
        Log.d("list", allBusinesses.toString());

        for (int i=0; i < 5; i++) {
            Random r = new Random();
            int low = 0;
            int high = allBusinesses.size() - 1;
            int randNum = r.nextInt(high-low) + low;
            businessesName.add(allBusinesses.get(randNum).getName());
            businessesImg.add(allBusinesses.get(randNum).getImageUrl());
            businesessLoc.add((allBusinesses.get(randNum).getLocation().getAddress1() + ", " +
                               allBusinesses.get(randNum).getLocation().getCity() + ", " +
                               allBusinesses.get(randNum).getLocation().getState() + " " +
                               allBusinesses.get(randNum).getLocation().getZipCode()));
            businesessDist.add(allBusinesses.get(randNum).getDistance());
        }

        businessListView adapter = new businessListView(this, businessesImg, businessesName, businesessLoc, businesessDist);
        businessList.setAdapter(adapter);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
