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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class FlightFragment extends Fragment {

    Button searchBtn;
    EditText From, To, Doj, Tclass, Nop;
    private final String flightSearchRoute = "auth/flight";

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

                JSONObject req = new JSONObject();
                try {
                    req.put("from", from);
                    req.put("to", to);
                    req.put("date_of_journey", doj);
                    req.put("ticket_class", tclass);
                    req.put("nop", nop);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }

                String flightSearchUrl = GlobalCtx.urlPrefix + flightSearchRoute;
                JsonObjectRequest flightSearchRequest = new JsonObjectRequest(Request.Method.GET, flightSearchUrl,
                        req, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Toast.makeText(this, "Search Successful", Toast.LENGTH_SHORT).show();

                        Activity context = getActivity();
                        Intent resultPage = new Intent(context, FlightBooking.class);
                        Bundle args = new Bundle();
                        args.putSerializable("response",(Serializable)response);
                        resultPage.putExtra("myKey",args);
                        FlightFragment.this.startActivity(resultPage);
                        //FlightFragment.this.finish();
                        GlobalCtx.flightResult = response;
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Something went wrong :-(",Toast.LENGTH_LONG).show();
                    }
                });

                GlobalCtx.queue.add(flightSearchRequest);
            }
        });
    }
}