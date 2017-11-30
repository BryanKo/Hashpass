package com.example.david.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Bryan on 11/28/2017.
 */

public class businessInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_current);

        // Element declaration connecting to XML elements (c = clicked)
        TextView cBusinessName = (TextView) findViewById(R.id.tvClickedBusinessName);
        TextView cBusinessAddr = (TextView) findViewById(R.id.tvClickedBusinessAddr);
        TextView cBusinessPrice = (TextView) findViewById(R.id.tvClickedBusinessPrice);
        TextView cBusinessRating = (TextView) findViewById(R.id.tvClickedBusinessRate);
        TextView cBusinessRev = (TextView) findViewById(R.id.tvClickedBusinessReviewCount);
        TextView cBusinessDist = (TextView) findViewById(R.id.tvClickedBusinessDist);

        Bundle extras = getIntent().getExtras();
        String currbusinessImg = extras.getString("passBusinessImg");
        String currBusinessName = extras.getString("passBusinessName");
        String currBusinessLoc = extras.getString("passBusinessLoc");
        double currBusinessDist = extras.getDouble("passBusinessDist");

        String currBusinessPrice = extras.getString("passBusinessPrice");
        double passBusinessRating = extras.getDouble("passBusinessRating");
        int passBusinessReviewCnt = extras.getInt("passBusinessReviewCnt");

        Log.d("bundleinfo", currbusinessImg + " " + currBusinessName + " " + currBusinessLoc + " " + currBusinessPrice + " " + passBusinessRating + " " + passBusinessReviewCnt + String.valueOf(round((currBusinessDist / 1609.34), 2)).concat(" miles"));

        // Call pulled-API info into view
        cBusinessName.setText(currBusinessName);
        cBusinessAddr.setText(currBusinessLoc);
        cBusinessPrice.setText(currBusinessPrice);
        cBusinessRating.setText(passBusinessRating + " / 5");
        cBusinessRev.setText(passBusinessReviewCnt + " Reviews");
        cBusinessDist.setText(String.valueOf(round((currBusinessDist / 1609.34), 2)).concat(" mi"));
    }



    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
