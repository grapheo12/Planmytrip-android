package com.example.planmytrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    EditText Name, Email, Dob, Password, Password2, Phone;
    private final String regRoute = "auth/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Name = (EditText)findViewById(R.id.regName);
        Email = (EditText)findViewById(R.id.regEmail);
        Dob = (EditText)findViewById(R.id.regDate);
        Password = (EditText)findViewById(R.id.regPassword);
        Password2 = (EditText)findViewById(R.id.regPassword2);
        Phone = (EditText)findViewById(R.id.regPhone);
    }

    public void doRegister(View view) throws JSONException {
        String name = Name.getText().toString();
        String email = Email.getText().toString();
        String dob = Dob.getText().toString();
        String password = Password.getText().toString();
        String password2 = Password2.getText().toString();
        String phone = Phone.getText().toString();


        if (!password.equals(password2)) {
            Toast.makeText(getApplicationContext(),
                    "Passwords do not match",
                    Toast.LENGTH_LONG).show();
            return;
        }

        JSONObject req = new JSONObject();
        req.put("email", email);
        req.put("password", password);
        req.put("name", name);
        req.put("dob", dob);
        req.put("phone", phone);

        String regUrl = GlobalCtx.urlPrefix + regRoute;
        JsonObjectRequest regRequest = new JsonObjectRequest(Request.Method.POST, regUrl,
                req, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Registration Successful",
                        Toast.LENGTH_SHORT).show();

                Intent mainPage = new Intent(Registration.this, MainActivity.class);
                Registration.this.startActivity(mainPage);
                Registration.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Something went wrong :-(",
                        Toast.LENGTH_LONG).show();
            }
        });

        GlobalCtx.queue.add(regRequest);
    }


}