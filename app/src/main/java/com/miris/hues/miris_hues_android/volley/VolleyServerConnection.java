package com.miris.hues.miris_hues_android.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Null on 2017-10-01.
 */

public class VolleyServerConnection {
    private static VolleyServerConnection instance;
    private RequestQueue rq;
    private ImageLoader il;
    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

    private VolleyServerConnection(Context context) {
        rq = Volley.newRequestQueue(context);
        il = new ImageLoader(rq, new ImageLoader.ImageCache() {
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

    public static VolleyServerConnection getInstance(Context context) {
        if (instance == null) {
            return new VolleyServerConnection(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return rq;
    }

    public ImageLoader getImageLoader() {
        return il;
    }
}
