package com.example.planmytrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    EditText email, password;
    EditText backend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button)findViewById(R.id.btnLogin);
        email = (EditText)findViewById(R.id.editEmail);
        password = (EditText)findViewById(R.id.editPwd);
        backend = (EditText)findViewById(R.id.backend);
    }

    public void doLogin(View view) throws JSONException {
        String user_email = email.getText().toString();
        String user_pwd = password.getText().toString();

        GlobalCtx.init(this, backend.getText().toString());
        GlobalCtx.queue.start();

        JSONObject req = new JSONObject();
        req.put("email", user_email);
        req.put("password", user_pwd);
        String loginRoute = "auth/login";

        Intent mainPage = new Intent(MainActivity.this, Landing.class);
        MainActivity.this.startActivity(mainPage);
        MainActivity.this.finish();

//        String loginUrl = GlobalCtx.urlPrefix + loginRoute;
//        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, loginUrl,
//                req, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(getApplicationContext(),
//                        "Login Successful",
//                        Toast.LENGTH_SHORT).show();
//
//                Intent mainPage = new Intent(MainActivity.this, Landing.class);
//                MainActivity.this.startActivity(mainPage);
//                MainActivity.this.finish();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),
//                        "Wrong Credentials",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//        GlobalCtx.queue.add(loginRequest);
    }
}