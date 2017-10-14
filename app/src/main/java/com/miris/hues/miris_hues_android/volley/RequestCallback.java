package com.miris.hues.miris_hues_android.volley;

import org.json.JSONObject;

/**
 * Created by secret on 10/14/17.
 */

public interface RequestCallback {
    void success(JSONObject response);
    void error(Throwable throwable);
}