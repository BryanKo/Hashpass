package com.example.david.testing;

import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class foodCurrent extends AppCompatActivity {
    //editviews to get user input for parameters for the yelp call
    EditText etType;
    EditText etLoc;
    EditText etRadius;
    String openNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_current);

        etType = (EditText) findViewById(R.id.etType);
        etLoc = (EditText) findViewById(R.id.etLoc);
        etRadius = (EditText) findViewById(R.id.etRadius);
        openNow = "true";
    }

    public void searchFood(View view) {
        if(TextUtils.isEmpty(etType.getText().toString()) || TextUtils.isEmpty(etLoc.getText().toString()) || TextUtils.isEmpty(etRadius.getText().toString())) {
            Toast.makeText(this, "Please fill out all parameters.", Toast.LENGTH_SHORT).show();     //if any of the edit views is left empty we pop up a toast to notify the user that they must fill all fields.
        } else {
            //code here fetches the user input from the appropriate fields and converts it to a string
            String keyword = etType.getText().toString();
            String location = etLoc.getText().toString();
            String radius = etRadius.getText().toString();

            //below code transfers the user inputed variables from this activity to the main activity.
            Intent intent = new Intent(this, foodContent.class);
            Bundle extras = new Bundle();
            extras.putString("keyword",keyword);
            extras.putString("location",location);
            extras.putString("radius",radius);
            extras.putString("openNow", openNow);
            intent.putExtras(extras);
            startActivity(intent);
        }

    }

}
