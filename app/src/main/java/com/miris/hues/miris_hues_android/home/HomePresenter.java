package com.miris.hues.miris_hues_android.home;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miris.hues.miris_hues_android.volley.VolleyServerConnection;

import org.json.JSONObject;

import static com.miris.hues.miris_hues_android.volley.ConnectionStatus.networkErrorListener;
import static com.miris.hues.miris_hues_android.volley.ConnectionStatus.networkSuccessListener;

/**
 * Created by Null on 2017-10-01.
 */

public class HomePresenter implements HomeContract.UserAction {
    private HomeContract.View mMainView;
    public static boolean DEBUG = true;

    public HomePresenter(HomeContract.View view) {
        this.mMainView = view;
    }

    @Override
    public void jsonDataGetClicked() {
        final RequestQueue rq = VolleyServerConnection.getInstance((MainActivity) mMainView).getRequestQueue();

        JsonObjectRequest jsonRQ = new JsonObjectRequest(Request.Method.GET,
                "https://randomuser.me/api/?results=10",
                new JSONObject(),
                networkSuccessListener(),
                networkErrorListener());
        rq.add(jsonRQ);
    }
}
