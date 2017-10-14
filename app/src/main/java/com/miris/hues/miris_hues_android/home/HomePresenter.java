package com.miris.hues.miris_hues_android.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miris.hues.miris_hues_android.R;
import com.miris.hues.miris_hues_android.adapter.CognitiveAdapter;
import com.miris.hues.miris_hues_android.data.CognitiveTextData;
import com.miris.hues.miris_hues_android.data.FCMKeyString;
import com.miris.hues.miris_hues_android.data.PropertiesUtil;
import com.miris.hues.miris_hues_android.data.SharedStore;
import com.miris.hues.miris_hues_android.log.Loging;
import com.miris.hues.miris_hues_android.volley.VolleyServerConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Null on 2017-10-01.
 */

public class HomePresenter implements HomeContract.UserAction {
    private HomeContract.View mMainView;
    public static boolean DEBUG = true;
    private Gson gson;
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String chatChannel = "fcm";

    public HomePresenter(HomeContract.View view) {
        this.mMainView = view;

        PropertiesUtil.getInstance().setup((MainActivity) mMainView);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((MainActivity) mMainView);

        recyclerView = (RecyclerView) ((MainActivity) mMainView).findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        getToken();
        initRealDatabase();
    }

    public void initRealDatabase() {
        // 데이터 베이스 이벤트 감지
        DatabaseReference evtReference = firebaseDatabase.getReference(chatChannel);
        // 메시지 컬럼을 기준으로 정렬하고 뒤에서부터 5개만 가져온다.
        // evtReference.orderByChild("msg").limitToFirst(5).addChildEventListener(new ChildEventListener() {
        evtReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void sendDatabase(FCMKeyString f) {
        databaseReference.child(chatChannel).push().setValue(f);
    }

    private void getToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            Log.i("mainActivityToken", token);
            FCMKeyString f = new FCMKeyString("kim", token);

            if (SharedStore.getString((MainActivity) mMainView, "kim").equals("")) {
                sendDatabase(f);
            }
            else if (SharedStore.getString((MainActivity) mMainView, "kim").equals(token)) {
                sendDatabase(f);
            }
            SharedStore.setString((MainActivity) mMainView, "kim", token);
        }
    }

    @Override
    public void jsonDataGetClicked() {
        String url = PropertiesUtil.getInstance().getProperty("cognitiveurl");

        final RequestQueue rq = VolleyServerConnection.getInstance((MainActivity) mMainView).getRequestQueue();

        JsonObjectRequest jsonRQ = new JsonObjectRequest(Request.Method.GET,
                url,
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
                    Loging.i(response.getString("regions"));
                    List<CognitiveTextData> persons = Arrays.asList(gson.fromJson(response.getString("regions"), CognitiveTextData[].class));
                    recyclerView.setAdapter(new CognitiveAdapter((MainActivity) mMainView, persons));
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
