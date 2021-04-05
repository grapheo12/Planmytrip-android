package com.example.planmytrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class activity_flight extends AppCompatActivity {

    Button searchBtn;
    EditText From, To, Doj, Tclass, Nop;
    private final String flightSearchRoute = "auth/flight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        From = (EditText)findViewById(R.id.from);
        To = (EditText)findViewById(R.id.to);
        Doj = (EditText)findViewById(R.id.doj);
        Tclass = (EditText)findViewById(R.id.tclass);
        Nop = (EditText)findViewById(R.id.nop);
    }

    public void searchFlight(View view) throws JSONException {
        String from = From.getText().toString();
        String to = To.getText().toString();
        String doj = Doj.getText().toString();
        String tclass = Tclass.getText().toString();
        int nop = Integer.parseInt(Nop.getText().toString());

        JSONObject req = new JSONObject();
        req.put("from", from);
        req.put("to", to);
        req.put("date_of_journey", doj);
        req.put("ticket_class", tclass);
        req.put("nop", nop);

        String flightSearchUrl = GlobalCtx.urlPrefix + flightSearchRoute;
        JsonObjectRequest flightSearchRequest = new JsonObjectRequest(Request.Method.GET, flightSearchUrl,
                req, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Search Successful",
                        Toast.LENGTH_SHORT).show();

                Intent mainPage = new Intent(activity_flight.this, MainActivity.class);
                activity_flight.this.startActivity(mainPage);
                activity_flight.this.finish();
                GlobalCtx.flightResult = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Something went wrong :-(",
                        Toast.LENGTH_LONG).show();
            }
        });

        GlobalCtx.queue.add(flightSearchRequest);
    }
}