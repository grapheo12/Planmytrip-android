package com.example.planmytrip.ui.hotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.planmytrip.GlobalCtx;
import com.example.planmytrip.HotelViewFragment;
import com.example.planmytrip.MyHotelViewRecyclerViewAdapter;
import com.example.planmytrip.R;
import com.example.planmytrip.dummy.DummyContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotelFragment extends Fragment {

    private HotelViewModel hotelViewModel;

    EditText checkIn, checkOut, city;
    Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotelViewModel =
                new ViewModelProvider(this).get(HotelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hotel, container, false);
        checkIn = (EditText)root.findViewById(R.id.editTextDate2);
        checkOut = (EditText)root.findViewById(R.id.editTextDate3);
        city = (EditText)root.findViewById(R.id.textView13);
        btn = (Button)root.findViewById(R.id.btnChkHotel);
        Fragment results = new HotelViewFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.hotel_search_results, results).commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hotelUrl = GlobalCtx.urlPrefix + "hotel/";
                JsonRequest request = new JsonObjectRequest(Request.Method.GET,
                        hotelUrl + "?city=" + city.getText().toString() +
                                "&start_date=" + checkIn.getText().toString() +
                                "&end_date=" + checkOut.getText().toString(),
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = (JSONArray)response.get("hotels");

                            if (arr.length() == 0){
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "No hotels found",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                List<DummyContent.DummyItem> res = new ArrayList<>();
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject obj = (JSONObject)arr.get(i);
                                    res.add(new DummyContent.DummyItem(
                                            (int)obj.get("hotel_id"),
                                            (String)obj.get("hotel_name"),
                                            (String)obj.get("city"),
                                            Float.parseFloat((String)obj.get("min_cost"))
                                    ));
                                }

                                ((RecyclerView)results.getView()).setAdapter(new MyHotelViewRecyclerViewAdapter(res));
                            }
                        }catch (Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });

                GlobalCtx.queue.add(request);
            }
        });


        return root;
    }
}