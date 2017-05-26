package com.jeeva.volley.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    // Singleton object...
    private static VolleySingleton instance;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    //Constructor...
    private VolleySingleton(Context context) {

        requestQueue = Volley.newRequestQueue(context);

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(100000000);


            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    // Singleton method...
    public static VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public ImageLoader getImageLoader(Context context) {
        if (imageLoader != null) {
            return imageLoader;
        } else {
            getInstance(context);
            return imageLoader;
        }
    }

    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue != null) {
            return requestQueue;
        } else {
            getInstance(context);
            return requestQueue;
        }
    }

    private RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        if (req.getTag() == null) {
            req.setTag("App");
        }

        getRequestQueue().add(req);
    }


}
