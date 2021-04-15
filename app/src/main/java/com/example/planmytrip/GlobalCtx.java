package com.example.planmytrip;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.planmytrip.ui.flight.FlightResult;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;

public class GlobalCtx {
    public static RequestQueue queue;
    public static String urlPrefix;
    public static JSONObject flightResult = null;
    public static ArrayList<FlightResult> flightResults = null;
    public static void init(Context ctx, String url) {
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        queue = Volley.newRequestQueue(ctx);
        urlPrefix = url;
    }

}
