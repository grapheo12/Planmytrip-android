package com.example.planmytrip;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;

public class GlobalCtx {
    static RequestQueue queue;
    static String urlPrefix;
    public static void init(Context ctx, String url) {
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        queue = Volley.newRequestQueue(ctx);
        urlPrefix = url;
    }

}
