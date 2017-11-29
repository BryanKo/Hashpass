package com.example.david.testing;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        //Log.d("latlong", String.valueOf(currLat) + " " + String.valueOf(currLng));
        Log.d("temperature", String.valueOf(currTemp[0]));
        Log.d("weather", String.valueOf(currWeather[0]));


        if (currTemp[0] < 60.0 && currWeather[0].equalsIgnoreCase("Clear")) {
            Log.d("checking", String.valueOf(currTemp[0])+ ", " + String.valueOf(currWeather[0]) + ": True");
        } else {
            Log.d("checking", String.valueOf(currTemp[0])+ ", " + String.valueOf(currWeather[0]) + ": False");
        }


        apiFactory = new YelpFusionApiFactory();
       if (currTemp[0] < 70 && currWeather[0].equalsIgnoreCase("Partly Cloudy")) {
            try {
                // Api call with client id and client secret id
                YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

                // Hashmap to store parameters
                Map<String, String> params = new HashMap<>();
                params.put("term", "food");
                params.put("radius", "16000");              // 16000 meters = 10 mile radius; radius is calculated in meters
                params.put("latitude", String.valueOf(currLat));
                params.put("longitude", String.valueOf(currLng));
                params.put("open_now", "false");            // false for now b/c it'll crash if nothing is found (will remove when open_at is implemented
                //params.put("open_at", );                  // for future when david pushes work to masters
                params.put("limit", "50");

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
        } else {
            try {
                // Api call with client id and client secret id
                YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

                // Hashmap to store parameters
                Map<String, String> params = new HashMap<>();
                params.put("term", "chinese food");
                params.put("radius", "16000");              // 16000 meters = 10 mile radius; radius is calculated in meters
                params.put("latitude", String.valueOf(currLat));
                params.put("longitude", String.valueOf(currLng));
                params.put("open_now", "false");            // false for now b/c it'll crash if nothing is found
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
        //Log.d("businesssize", String.valueOf(allBusinesses.size()));

        for (int i = businessIndex; i < businesses.size(); i++) {
            allBusinesses.add(businesses.get(i));
            businessIndex++;
        }
        Log.d("list", String.valueOf(allBusinesses.size()));

/*        for (int i=0; i < allBusinesses.size(); i++) {
            businessesName.add(allBusinesses.get(i).getName());
            businessesImg.add(allBusinesses.get(i).getImageUrl());
            businesessLoc.add((allBusinesses.get(i).getLocation().getAddress1() + ", " +
                    allBusinesses.get(i).getLocation().getCity() + ", " +
                    allBusinesses.get(i).getLocation().getState() + " " +
                    allBusinesses.get(i).getLocation().getZipCode()));
            businesessDist.add(allBusinesses.get(i).getDistance());
        }*/

        for (int i=0; i < 5; i++) {
            int low = 0;
            int high = allBusinesses.size();
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

        businessList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(businessesName != null) {
                    Toast.makeText(getApplicationContext(), businessesName.get(+position), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(foodCurrent.this, businessInfo.class);
                    Bundle extras = new Bundle();
                    extras.putString("passBusinessName", businessesName.get(+position));
                    //extras.put("passBusinessImg", businessesImg.get(+position));
                    extras.putString("passBusinessLoc", businesessLoc.get(+position));
                    extras.putDouble("passBusinessDist", businesessDist.get(+position));
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }
}
