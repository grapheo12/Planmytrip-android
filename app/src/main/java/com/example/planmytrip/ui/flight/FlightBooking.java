package com.example.planmytrip.ui.flight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.planmytrip.GlobalCtx;
import com.example.planmytrip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_booking);

        //Bundle args = getIntent().getBundleExtra("myKey");
        ArrayList<FlightResult> flightResults = GlobalCtx.flightResults;

        //JSONArray resultArray = result.getJSONArray("flights");
        //ArrayList<FlightResult> flightResults = FlightResult.fromJson(resultArray);
        FlightResultAdapter adapter = new FlightResultAdapter(this, flightResults);
        ListView listView = (ListView)findViewById(R.id.tvItems);
        listView.setAdapter(adapter);

    }
}