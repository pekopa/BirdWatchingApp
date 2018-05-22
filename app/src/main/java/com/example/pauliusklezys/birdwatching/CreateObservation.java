package com.example.pauliusklezys.birdwatching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CreateObservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_observation);

        Button createObservation = findViewById(R.id.create_button);

        EditText englishName = findViewById(R.id.english_name);
        String englishNameString = englishName.getText().toString();

        EditText comment = findViewById(R.id.comment);
        String commentString = comment.getText().toString();

        EditText danishName = findViewById(R.id.danish_name);
        String danishNameString = danishName.getText().toString();

        EditText placename = findViewById(R.id.placename);
        String placenameString = placename.getText().toString();

        EditText population = findViewById(R.id.population);
        String populationString = population.getText().toString();

        EditText date = findViewById(R.id.datetime);
        String dateString = date.getText().toString();

        createObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Observation newObservation = new Observation(1, commentString);*/
            }
        });
    }

    public void PostObservation() {
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://birdobservationservice.azurewebsites.net/Service1.svc/observations";
        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Connection eror", Toast.LENGTH_LONG).show();
            }
        }) {
        /*    @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("BirdId", );
                params.put("pass", userAccount.getPassword());
                params.put("comment", Uri.encode(comment));
                params.put("comment_post_ID", String.valueOf(postId));
                params.put("blogId", String.valueOf(blogId));

                return params;
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
