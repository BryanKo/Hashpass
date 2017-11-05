package com.example.david.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import retrofit2.Call;

/**
 * Created by imbko on 11/5/2017.
 */

public class foodContent extends AppCompatActivity {
    TextView businessName, businessLoc, businessRating, businessPrice, businessDist;
    String appId = "3v_MqsnS4xUByPuMTTjKZw";
    String appSecret = "41AchC7qNowuPe2y2GPUnGPj4Xc25h9SRCEyuSzU7QYZKq6gzfgTUyyHpu69PohB";
    int businessIndex = 0;
    ArrayList<Business> businesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_current);

        TextView businessName = (TextView) findViewById(R.id.tvBusinessName);
        TextView businessLoc = (TextView) findViewById(R.id.tvAddress);
        TextView businessRating = (TextView) findViewById(R.id.tvRating);
        TextView businessPrice = (TextView) findViewById(R.id.tvPrice);
        TextView businessDist = (TextView) findViewById(R.id.tvDistance);

        Bundle extras = getIntent().getExtras();
        String kword = extras.getString("keyword");
        String location = extras.getString("location");
        String openNow = extras.getString("openNow");
        String radius = extras.getString("radius");
        String radiusString = "";

        if (radius != null) {
            int radiusInt = ((Integer.parseInt(radius)) * 1610);
            radiusString = Integer.toString(radiusInt);
        }

        // get yelp api auth key
        YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
        try {
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);

            Map<String, String> params = new HashMap<>();
            params.put("term", kword);
            params.put("radius", radiusString);
            params.put("location", location);
            params.put("open_now", openNow);

            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
            SearchResponse searchResponse = call.execute().body();

            businesses = searchResponse.getBusinesses();

            Business business = businesses.get(businessIndex);

            businessName.setText(business.getName());
/*            businessLoc.setText(business.getLocation().getAddress1() + ", " +
                                business.getLocation().getCity() + ", " +
                                business.getLocation().getState() + " " +
                                business.getLocation().getZipCode());*/
            //businessRating.setText(String.valueOf(business.getRating()));
            //businessPrice.setText(business.getPrice());
            //businessDist.setText(String.valueOf(round((business.getDistance() / 1609.34),2)).concat(" miles"));


            Log.d("Token", "Successful");
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }
}
