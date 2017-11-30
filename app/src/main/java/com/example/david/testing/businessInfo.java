package com.example.david.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Bryan on 11/28/2017.
 */

public class businessInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses_current);

        Bundle extras = getIntent().getExtras();
        String currbusinessImg = extras.getString("passBusinessImg");
        String currBusinessName = extras.getString("passBusinessName");
        String currBusinessLoc = extras.getString("passBusinessLoc");
        double currBusinessDist = extras.getDouble("passBusinessDist");

        String currBusinessPrice = extras.getString("passBusinessPrice");
        double passBusinessRating = extras.getDouble("passBusinessRating");
        int passBusinessReviewCnt = extras.getInt("passBusinessReviewCnt");

        Log.d("bundleinfo", currbusinessImg + " " + currBusinessName + " " + currBusinessLoc + " " + currBusinessPrice + " " + passBusinessRating + " " + passBusinessReviewCnt + String.valueOf(round((currBusinessDist / 1609.34), 2)).concat(" miles"));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
