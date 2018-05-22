package com.example.pauliusklezys.birdwatching;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DisplayObservation extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_observation);

        Intent intent = getIntent();
        final Observation observation = (Observation) intent.getSerializableExtra("observation");

        ImageView bird = findViewById(R.id.bird);
        Picasso.with(getBaseContext())
                .load(observation.getPhotoUrl())
                .into(bird);

        TextView nameEnglish = findViewById(R.id.name_english);
        nameEnglish.setText(observation.getNameEnglish());

        TextView nameDanish = findViewById(R.id.name_danish);
        nameDanish.setText(observation.getNameDanish());

        Calendar calendar = JsonToDate.JsonDateToDate(observation.getCreated());
        long unixSeconds = calendar.getTimeInMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC+2"));
        String formattedDate = sdf.format(unixSeconds);

        TextView created = findViewById(R.id.posted);
        created.setText(formattedDate);

        TextView comment = findViewById(R.id.comment);
        comment.setText(observation.getComment());

        TextView placename = findViewById(R.id.placename);
        placename.setText(observation.getPlacename());

        TextView population = findViewById(R.id.population);
        int populationId = observation.getPopulation();
        String populationString = Integer.toString(populationId);
        population.setText(populationString);

        Button mapButton = findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentForMap = new Intent(getBaseContext(), MapsActivity.class);
                intentForMap.putExtra("observation", observation);
                startActivity(intentForMap);
            }
        });
    }
}