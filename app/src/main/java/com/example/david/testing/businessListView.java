package com.example.david.testing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/28/2017.
 */

public class businessListView extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> businessesName;
    private ArrayList<String> businessesImg;
    private ArrayList<String> businesessAdr;
    private ArrayList<Double> businessesDist;

    public businessListView(Activity context,
                            ArrayList<String> businessesImg,
                            ArrayList<String> businessesName,
                            ArrayList<String> businesessAdr,
                            ArrayList<Double> businessesDist) {
        super(context, R.layout.list_view_businesses_layout, businessesName);
        this.context = context;
        this.businessesImg = businessesImg;
        this.businessesName = businessesName;
        this.businesessAdr = businesessAdr;
        this.businessesDist = businessesDist;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View businessInfo=inflater.inflate(R.layout.list_view_businesses_layout, null,true);

        ImageView businessImg = (ImageView) businessInfo.findViewById(R.id.ivBusinesses);
        TextView businessName = (TextView) businessInfo.findViewById(R.id.tvBusinessName);
        TextView businessAdr = (TextView) businessInfo.findViewById(R.id.tvBusinessAdr);
        TextView businessDist = (TextView) businessInfo.findViewById(R.id.tvBusinessDist);

        new DownloadImageTask((ImageView) businessInfo.findViewById(R.id.ivBusinesses))
                .execute(businessesImg.get(position));

        businessName.setText(businessesName.get(position));
        businessAdr.setText(businesessAdr.get(position));
        //businessDist.setText(businessesDist.get(position).toString());
        businessDist.setText(String.valueOf(round((businessesDist.get(position) / 1609.34), 2)).concat(" miles"));
        return businessInfo;
    };

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
