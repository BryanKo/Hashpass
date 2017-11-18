package com.example.david.testing;

import android.support.v4.app.Fragment;
import android.app.Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by David on 11/17/2017.
 */

public class ListViewAdapter extends Activity{
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.scroll_items, container, false);
//
//
//        String[] things = {"here", "potato", "november", "foods", "crazy", "banana", "activity", "saltine", "iphone"};
//        ListAdapter myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, things);
//        ListView myListView = (ListView) view.findViewById(R.id.listview);
//        myListView.setAdapter(myAdapter);
//        return view;
//    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.time);

            String[] things = {"here", "potato", "november", "foods", "crazy", "banana", "activity", "saltine", "iphone"};
            ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, things);
            ListView myListView = (ListView) findViewById(R.id.listview);
            myListView.setAdapter(myAdapter);

        }
}
