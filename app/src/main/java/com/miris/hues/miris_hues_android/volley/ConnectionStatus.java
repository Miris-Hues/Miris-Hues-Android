package com.miris.hues.miris_hues_android.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.miris.hues.miris_hues_android.log.Loging;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Null on 2017-10-01.
 */

public class ConnectionStatus {
    public static Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Loging.i("Network Success Loaded");
                    Loging.i(response.getString("results"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Loging.i("Network Filed Loaded");
            }
        };
    }
}
