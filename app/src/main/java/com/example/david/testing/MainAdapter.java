package com.example.david.testing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by David on 11/29/2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private ArrayList<DataModel> allDataArray;
    //Get Calendar and Date to display NOW and the next 3 hours;
    // I (david) am pretty sure Calendar, DateFormat ,and Date are not needed here
    // as they are already used in MainActivity
    // but I will keep them here until I figure it out (12/2/17)
    Calendar calendar = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("hh a");
    Date date = new Date();

    public MainAdapter(ArrayList<DataModel> allDataArray){
        this.allDataArray = allDataArray;
    }


    public Object getItem (int i){
        return allDataArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder holder, int position) {
        DataModel dataModel = allDataArray.get(position);
        holder.mTime.setText(dataModel.getTimeText());
        holder.mFood.setText(dataModel.getFoodText());
        holder.mBar.setText(dataModel.getBarText());
        holder.mActive.setText(dataModel.getActiveText());
        holder.mIndoor.setText(dataModel.getIndoorText());
        holder.mCurrWeather = dataModel.getCurrWeather();
        holder.mCurrTemp = dataModel.getCurrTemp();
        holder.mAxx = dataModel.getAxx();
        holder.mAyy = dataModel.getAyy();
        holder.mPassTime = dataModel.getPassTime();

        Log.d("passTime", String.valueOf(holder.mPassTime));

        holder.mFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, foodCurrent.class);
                Bundle extras = new Bundle();
                extras.putStringArray("passCurrWeather", holder.mCurrWeather);
                extras.putDoubleArray("passCurrTemp", holder.mCurrTemp);
                extras.putDouble("passCurrLat", holder.mAxx);
                extras.putDouble("passCurrLng", holder.mAyy);
                extras.putDouble("passCurrTime", holder.mPassTime);
                intent.putExtras(extras);
                context.startActivity(intent);
               // Log.d("tag", "button pressed");
            }
        });
        holder.mBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, barCurrent.class);
                Bundle extras = new Bundle();
                extras.putStringArray("passCurrWeather", holder.mCurrWeather);
                extras.putDoubleArray("passCurrTemp", holder.mCurrTemp);
                extras.putDouble("passCurrLat", holder.mAxx);
                extras.putDouble("passCurrLng", holder.mAyy);
                extras.putDouble("passCurrTime", holder.mPassTime);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
        holder.mActive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, activeCurrent.class);
                Bundle extras = new Bundle();
                extras.putStringArray("passCurrWeather", holder.mCurrWeather);
                extras.putDoubleArray("passCurrTemp", holder.mCurrTemp);
                extras.putDouble("passCurrLat", holder.mAxx);
                extras.putDouble("passCurrLng", holder.mAyy);
                extras.putDouble("passCurrTime", holder.mPassTime);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
        holder.mIndoor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, indoorCurrent.class);
                Bundle extras = new Bundle();
                extras.putStringArray("passCurrWeather", holder.mCurrWeather);
                extras.putDoubleArray("passCurrTemp", holder.mCurrTemp);
                extras.putDouble("passCurrLat", holder.mAxx);
                extras.putDouble("passCurrLng", holder.mAyy);
                extras.putDouble("passCurrTime", holder.mPassTime);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return allDataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTime;
        public Button mFood;
        public Button mBar;
        public Button mActive;
        public Button mIndoor;
        public String[] mCurrWeather;
        public double[] mCurrTemp;
        public double mAxx;
        public double mAyy;
        public double mPassTime;

        public ViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            mTime = (TextView) itemView.findViewById(R.id.time_time);
            mFood = (Button) itemView.findViewById(R.id.foodButton);
            mBar = (Button) itemView.findViewById(R.id.barButton);
            mActive = (Button) itemView.findViewById(R.id.activeButton);
            mIndoor = (Button) itemView.findViewById(R.id.indoorButton);
        }
    }
}