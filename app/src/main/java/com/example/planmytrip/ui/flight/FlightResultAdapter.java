package com.example.planmytrip.ui.flight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.planmytrip.R;

import java.util.ArrayList;

public class FlightResultAdapter extends ArrayAdapter<FlightResult> {
    public FlightResultAdapter(Context context, ArrayList<FlightResult> results){
        super(context, 0, results);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FlightResult result = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_flight, parent, false);

        }

        // Lookup view for data population
        TextView tvFlightId  = (TextView) convertView.findViewById(R.id.tvFlightId);
        TextView tvAirlines  = (TextView) convertView.findViewById(R.id.tvAirlines);
        TextView tvDeparture = (TextView) convertView.findViewById(R.id.tvDeparture);
        TextView tvFare      = (TextView) convertView.findViewById(R.id.tvFare);
        TextView tvSeatType  = (TextView) convertView.findViewById(R.id.tvSeatType);
        TextView tvSeats     = (TextView) convertView.findViewById(R.id.tvSeats);

        // Populate the data into the template view using the data object
        tvFlightId.setText(result.flight_id);
        tvAirlines.setText(result.airlines);
        tvDeparture.setText(result.departure);
        tvFare.setText(Double.toString(result.fare));
        tvSeatType.setText(result.seat_type);
        tvSeats.setText(result.seats);

        // Return the completed view to render on screen

        return convertView;

    }
}
