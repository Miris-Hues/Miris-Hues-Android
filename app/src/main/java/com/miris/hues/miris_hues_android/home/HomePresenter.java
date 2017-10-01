package com.miris.hues.miris_hues_android.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miris.hues.miris_hues_android.R;
import com.miris.hues.miris_hues_android.adapter.UserViewAdapter;
import com.miris.hues.miris_hues_android.log.Loging;
import com.miris.hues.miris_hues_android.person.PersonModel;
import com.miris.hues.miris_hues_android.volley.VolleyServerConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Null on 2017-10-01.
 */

public class HomePresenter implements HomeContract.UserAction {
    private HomeContract.View mMainView;
    public static boolean DEBUG = true;
    private Gson gson;
    private RecyclerView recyclerView;

    public HomePresenter(HomeContract.View view) {
        this.mMainView = view;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((MainActivity) mMainView);

        recyclerView = (RecyclerView) ((MainActivity) mMainView).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
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

    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Loging.i("Network Success Loaded");
                    Loging.i(response.getString("results"));
                    List<PersonModel.Person> persons = Arrays.asList(gson.fromJson(response.getString("results"), PersonModel.Person[].class));
                    recyclerView.setAdapter(new UserViewAdapter((MainActivity) mMainView, persons));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Loging.i("Network Filed Loaded");
            }
        };
    }
}
