package com.example.david.testing;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    //Get Calendar and Date to display NOW and the next 5 hours;
    Calendar calendar = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("hh a");
    Date date = new Date();

    public MainAdapter(){
        allDataArray = new ArrayList<DataModel>(5);

        allDataArray.add (new DataModel("Now", "Food"));
        calendar.add(Calendar.HOUR, 1);
        Date oneHour = calendar.getTime();
        allDataArray.add (new DataModel(dateFormat.format(oneHour), "Food2"));
        calendar.add(Calendar.HOUR, 1);
        Date twoHour = calendar.getTime();
        allDataArray.add ( new DataModel(dateFormat.format(twoHour), "Food3"));
        calendar.add(Calendar.HOUR, 1);
        Date threeHour = calendar.getTime();
        allDataArray.add ( new DataModel(dateFormat.format(threeHour), "Food4"));
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
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        DataModel dataModel = allDataArray.get(position);
        holder.mTime.setText(dataModel.getTimeText());
        holder.mBut.setText(dataModel.getButtonText());
    }


    @Override
    public int getItemCount() {
        return allDataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTime;
        public Button mBut;

        public ViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            mTime = (TextView) itemView.findViewById(R.id.time_time);
            mBut = (Button) itemView.findViewById(R.id.button);
        }
    }
}