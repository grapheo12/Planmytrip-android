package com.example.planmytrip.ui.flight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.planmytrip.GlobalCtx;
import com.example.planmytrip.MainActivity;
import com.example.planmytrip.R;
import com.example.planmytrip.ui.hotel.HotelViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class FlightFragment extends Fragment {

    Button searchBtn;
    EditText From, To, Doj, Tclass, Nop;
    private final String flightSearchRoute = "flight/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flight, container, false);
        System.out.println(root.toString());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setContentView(R.layout.fragment_flight);

        From = (EditText)view.findViewById(R.id.from);
        To = (EditText)view.findViewById(R.id.to);
        Doj = (EditText)view.findViewById(R.id.doj);
        Tclass = (EditText)view.findViewById(R.id.tclass);
        Nop = (EditText)view.findViewById(R.id.nop);
        searchBtn = (Button)view.findViewById(R.id.search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = From.getText().toString();
                String to = To.getText().toString();
                String doj = Doj.getText().toString();
                String tclass = Tclass.getText().toString();
                int nop = Integer.parseInt(Nop.getText().toString());
                GlobalCtx.passengers = nop;

                JSONObject req = new JSONObject();
                try {
                    req.put("from", from);
                    req.put("to", to);
                    req.put("date_of_journey", doj);
                    req.put("ticket_class", tclass);
                    req.put("passengers", Integer.toString(nop));
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }

                String flightSearchUrl = GlobalCtx.urlPrefix + flightSearchRoute;
                JsonObjectRequest flightSearchRequest = new JsonObjectRequest(Request.Method.GET,
                        flightSearchUrl+"?from="+from+"&to="+to+"&date_of_journey="+doj+"&ticket_class="+tclass+"&passengers="+nop,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext().getApplicationContext(), "Search Successful", Toast.LENGTH_SHORT).show();

                        Activity context = getActivity();
                        Intent resultPage = new Intent(context, FlightBooking.class);
                        Bundle args = new Bundle();
                        try {
                            JSONArray flights = response.getJSONArray("flights");
                            System.out.println(flights.toString());
                            ArrayList<FlightResult> flightResults = FlightResult.fromJson(flights);
                            //args.putSerializable("response",(Serializable)flightResults);
                            //resultPage.putExtra("myKey",args);
                            FlightFragment.this.startActivity(resultPage);
                            GlobalCtx.flightResult = response;
                            GlobalCtx.flightResults = flightResults;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext().getApplicationContext(), "No flights found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(), "No flights found", Toast.LENGTH_SHORT).show();
                    }
                });

                GlobalCtx.queue.add(flightSearchRequest);
            }
        });
    }
}