package com.example.planmytrip.ui.flight;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.planmytrip.GlobalCtx;
import com.example.planmytrip.MainActivity;
import com.example.planmytrip.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightResultAdapter extends ArrayAdapter<FlightResult> {
    Button bookBtn;
    private final String flightSearchRoute = "flight/booking";
    public FlightResultAdapter(Context context, ArrayList<FlightResult> results){
        super(context, 0, results);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FlightResult result = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_flight, parent, false);

        // Lookup view for data population
        TextView tvFlightId  = (TextView) convertView.findViewById(R.id.tvFlightId);
        TextView tvAirlines  = (TextView) convertView.findViewById(R.id.tvAirlines);
        TextView tvDeparture = (TextView) convertView.findViewById(R.id.tvDeparture);
        TextView tvFare      = (TextView) convertView.findViewById(R.id.tvFare);
        TextView tvSeatType  = (TextView) convertView.findViewById(R.id.tvSeatType);
        TextView tvSeats     = (TextView) convertView.findViewById(R.id.tvSeats);
        TextView tvNop       = (TextView) convertView.findViewById(R.id.tvNop);

        // Populate the data into the template view using the data object
        tvFlightId.setText(String.valueOf(result.flight_id));
        tvAirlines.setText(result.airlines);
        tvDeparture.setText(result.departure);
        tvFare.setText(Double.toString(result.fare));
        tvSeatType.setText(result.seat_type);
        tvSeats.setText(String.valueOf(result.seats));
        tvNop.setText(String.valueOf(GlobalCtx.passengers));

        bookBtn = (Button)convertView.findViewById(R.id.book1);

        bookBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //OnCLick Stuff

                String flight_id = tvFlightId.getText().toString();
                String airlines = tvAirlines.getText().toString();
                String departure = tvDeparture.getText().toString();
                String seat_type = tvSeatType.getText().toString();
                int nop = Integer.parseInt(tvNop.getText().toString());
                double fare = Double.parseDouble(tvFare.getText().toString())/nop;
                int seats = Integer.parseInt(tvSeats.getText().toString());

                JSONObject req = new JSONObject();
                try {
                    req.put("flight_id", flight_id);
                    req.put("airlines", airlines);
                    req.put("departure", departure);
                    req.put("seat_type", seat_type);
                    req.put("fare", fare);
                    req.put("seats", nop);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }

                System.out.println("Booking called");
                Toast.makeText(getContext().getApplicationContext(),
                        "Booking Successful!",
                        Toast.LENGTH_SHORT).show();


                String route = GlobalCtx.urlPrefix + flightSearchRoute;
                JsonObjectRequest flightBookRequest = new JsonObjectRequest(Request.Method.POST, route,
                        req, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext().getApplicationContext(),
                                "Booking Successful!",
                                Toast.LENGTH_SHORT).show();

                        Context context = getContext().getApplicationContext();
                        Intent intent= new Intent(context, MainActivity.class);
                        context.startActivity(intent);

                        //Intent mainPage = new Intent(ItemFlight.this, MainActivity.class);
                        //ItemFlight.this.startActivity(mainPage);
                        //ItemFlight.this.finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(),
                                "Something went wrong :-(",
                                Toast.LENGTH_LONG).show();

                        Context context = getContext().getApplicationContext();
                        Intent intent= new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                GlobalCtx.queue.add(flightBookRequest);
            }
        });
        // Return the completed view to render on screen

        return convertView;

    }
}
