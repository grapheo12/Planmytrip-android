package com.example.planmytrip.ui.flight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.io.Serializable;

public class ItemFlight extends AppCompatActivity {

    Button bookBtn;
    TextView FlightId, Airlines, Departure, SeatType, Seats, Fare;
    EditText Nop;
    private final String flightSearchRoute = "auth/flight/booking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.item_flight);

        FlightId  = (TextView)findViewById(R.id.tvFlightId);
        Airlines  = (TextView)findViewById(R.id.tvAirlines);
        Departure = (TextView)findViewById(R.id.tvDeparture);
        SeatType  = (TextView)findViewById(R.id.tvSeatType);
        Fare      = (TextView)findViewById(R.id.tvFare);
        Seats     = (TextView)findViewById(R.id.tvSeats);
        Nop       = (EditText)findViewById(R.id.tvNop);

        bookBtn = (Button)findViewById(R.id.book1);

        bookBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //OnCLick Stuff
                String flight_id = FlightId.getText().toString();
                String airlines = Airlines.getText().toString();
                String departure = Departure.getText().toString();
                String seat_type = SeatType.getText().toString();
                double fare = Double.parseDouble(Fare.getText().toString());
                int seats = Integer.parseInt(Seats.getText().toString());
                int nop = Integer.parseInt(Nop.getText().toString());

                JSONObject req = new JSONObject();
                try {
                    req.put("flight_id", flight_id);
                    req.put("airlines", airlines);
                    req.put("departure", departure);
                    req.put("seat_type", seat_type);
                    req.put("fare", fare);
                    req.put("seats", seats);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }

                System.out.println("Booking called");

                String flightSearchUrl = GlobalCtx.urlPrefix + flightSearchRoute;
                JsonObjectRequest flightSearchRequest = new JsonObjectRequest(Request.Method.POST, flightSearchUrl,
                        req, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),
                                "Booking Successful!",
                                Toast.LENGTH_SHORT).show();

                        Intent mainPage = new Intent(ItemFlight.this, MainActivity.class);
                        ItemFlight.this.startActivity(mainPage);
                        ItemFlight.this.finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                "Something went wrong :-(",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}