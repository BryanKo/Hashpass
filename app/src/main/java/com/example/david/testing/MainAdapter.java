package com.example.david.testing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 11/17/2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private ArrayList<String> mDataSet;
//    private ArrayList<Button> mButtonSet;


    public MainAdapter(ArrayList<String> mDataSet){
        this.mDataSet = mDataSet;
//        this.mButtonSet = mButtonSet;
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
        holder.mTime.setText(mDataSet.get(position));
//        holder.mBut.setTag(mButtonSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTime;
  //      public Button mBut;


        public ViewHolder(View itemView) {
            super(itemView);

            mTime = (TextView) itemView.findViewById(R.id.time_time);
 //           mBut = (Button) itemView.findViewById(R.id.button);
        }
    }
}
