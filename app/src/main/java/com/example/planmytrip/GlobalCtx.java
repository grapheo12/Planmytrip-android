package com.example.planmytrip;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;

public class GlobalCtx {
    static RequestQueue queue;
    static String urlPrefix;
    static JSONObject flightResult = null;
    public static void init(Context ctx, String url) {
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        queue = Volley.newRequestQueue(ctx);
        urlPrefix = url;
    }

}
