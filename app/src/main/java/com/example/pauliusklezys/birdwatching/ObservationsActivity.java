package com.example.pauliusklezys.birdwatching;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservationsActivity extends AppCompatActivity {

    private Bird[] birds;
    private DrawerLayout mDrawerLayout;
    private FirebaseAuth auth;
    private Observation[] observations;
    private List<Observation> myObs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observations);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_home_black_24px);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newObservation = new Intent(getBaseContext(), CreateObservation.class);
                startActivity(newObservation);
            }
        });

        TextView listHeader = new TextView(this);
        listHeader.setText("Observations");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);

        LoadBirds();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch (menuItem.getItemId()) {
                            case R.id.nav_account_settings:
                                Intent navigateAccountSettings = new Intent(getBaseContext(), AccountSettingsActivity.class);
                                startActivity(navigateAccountSettings);
                                break;

                            case R.id.nav_sign_out:
                                auth.signOut();
                                Intent navigateLoginPage = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(navigateLoginPage);
                                break;

                            case R.id.nav_observations:
                                Intent navigateObservationsPage = new Intent(getBaseContext(), ObservationsActivity.class);
                                startActivity(navigateObservationsPage);
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void LoadBirds() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://birdobservationservice.azurewebsites.net/Service1.svc/birds";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();
                birds = gson.fromJson(response, Bird[].class);
                LoadObservations();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Connection eror", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void LoadObservations() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://birdobservationservice.azurewebsites.net/Service1.svc/observations";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();
                observations = gson.fromJson(response, Observation[].class);
                LoadMyObservations();

                if (birds != null) {
                    for (int i = 0; i < observations.length; i++) {
                        for (int x = 0; x < birds.length; x++) {
                            int id = observations[i].getBirdId();
                            int birdId = birds[x].getId();
                            if (birdId == id) {
                                observations[i].setPhotoUrl(birds[x].getPhotoUrl());
                                break;
                            }
                        }
                    }
                }

                // Adapter
                ListView listView = findViewById(R.id.all_observations_listview);

                ObservationListAdapter adapter = new ObservationListAdapter(getBaseContext(), R.layout.observation_item, observations);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), DisplayObservation.class);
                        Observation observation = observations[(int) id];
                        intent.putExtra("observation", observation);
                        startActivity(intent);

                        Intent intentForMap = new Intent(getBaseContext(), MapsActivity.class);
                        intentForMap.putExtra("observation", observation);
                        /*startActivity(intentForMap);*/
                    }
                });
            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Connection error", Toast.LENGTH_LONG).show();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void LoadMyObservations() {

        auth = FirebaseAuth.getInstance();


        Observation myObservation = new Observation(11, "This is my observation", "/Date(1519378162767+0000)/", 12, "55.6388484", "12.0875057", "Heaven", 1263, auth.getCurrentUser().getUid(), "Fiskehejre", "Heron", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Sangsvaner_stor.jpg/420px-Sangsvaner_stor.jpg");
        myObs.add(myObservation);

        Observation myObservationTwo = new Observation(12, "This is my observation two", "/Date(1519378162767+0000)/", 13, "55.6389777", "12.087865", "Heaven", 288, auth.getCurrentUser().getUid(), "Ringdue", "Wood pigeon", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Wood_pigeon_side_9l07.JPG/450px-Wood_pigeon_side_9l07.JPG");
        myObs.add(myObservationTwo);

        if (auth.getCurrentUser() != null) {
            for (Observation observation : observations) {
                String userId = observation.getUserId();

                if (auth.getCurrentUser().getUid().equals(userId)) {
                    myObs.add(observation);
                    break;
                }
            }
        }

        ListView listView = findViewById(R.id.my_observations_listview);

        ObservationListAdapter adapter = new ObservationListAdapter(getBaseContext(), R.layout.observation_item, myObs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), DisplayObservation.class);
                Observation observation = myObs.get((int) id);
                intent.putExtra("observation", observation);
                startActivity(intent);

                Intent intentForMap = new Intent(getBaseContext(), MapsActivity.class);
                intentForMap.putExtra("observation", observation);
            }
        });
    }
}