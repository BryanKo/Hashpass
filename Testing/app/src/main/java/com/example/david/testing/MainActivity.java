package com.example.david.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button currentFood = (Button) findViewById(R.id.button13);
        Button currentBar = (Button) findViewById(R.id.button14);
        Button currentActive = (Button) findViewById(R.id.button15);
        Button currentIndoor = (Button) findViewById(R.id.button16);

        currentFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, foodCurrent.class);
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
