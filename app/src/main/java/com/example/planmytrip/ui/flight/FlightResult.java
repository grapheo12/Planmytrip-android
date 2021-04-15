package com.example.planmytrip.ui.flight;

import com.example.planmytrip.GlobalCtx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightResult {
    public int flight_id;
    public String airlines;
    public String departure;
    public String seat_type;
    public double fare;
    public int seats;

    public FlightResult(JSONObject object)
    {
        try {
            this.flight_id = object.getInt("flight_id");
            this.airlines = object.getString("airlines");
            this.departure = object.getString("departure");
            this.fare = object.getDouble(("fare"))* GlobalCtx.passengers;
            this.seat_type = object.getString("seat_type");
            this.seats = object.getInt("seats");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<FlightResult> fromJson(JSONArray jsonObjects) {
        ArrayList<FlightResult> FlightResults = new ArrayList<FlightResult>();

        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                FlightResults.add(new FlightResult(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return FlightResults;
    }
}
