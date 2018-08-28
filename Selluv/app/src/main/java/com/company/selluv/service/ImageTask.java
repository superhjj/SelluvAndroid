package com.company.selluv.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImageTask extends AsyncTask<ImageView, Void, Bitmap> {

    private ImageView image;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {

        image = imageViews[0];

        try {
            String img = (String) imageViews[0].getTag();
            Log.d("img","img: "+img);
            URL url = new URL("http://192.168.30.33:8089"+img);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();
            Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());

            con.disconnect();
            return bitmap;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }
}