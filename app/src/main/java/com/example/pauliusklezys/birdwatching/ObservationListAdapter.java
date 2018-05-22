package com.example.pauliusklezys.birdwatching;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

    public class ObservationListAdapter extends ArrayAdapter<Observation> {
        private final int resource;

        public ObservationListAdapter(Context context, int resource, List<Observation> objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        public ObservationListAdapter(Context context, int resource, Observation[] objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            Observation observation = getItem(position);
            String nameEnglish = observation.getNameEnglish();
            String nameDanish = observation.getNameDanish();
            String photoUrl = observation.getPhotoUrl();
            LinearLayout observationView;
            if (convertView == null) {
                observationView = new LinearLayout(getContext());
                String inflater = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
                li.inflate(resource, observationView, true);
            } else {
                observationView = (LinearLayout) convertView;
            }
            TextView nameEnglishView = observationView.findViewById(R.id.observationlist_item_nameEnglish);
            TextView nameDanishView = observationView.findViewById(R.id.observationlist_item_nameDanish);
            ImageView photoUrlImage = observationView.findViewById(R.id.photo);
            nameEnglishView.setText(nameEnglish);
            nameDanishView.setText(nameDanish);

            Picasso.with(getContext())
                    .load(photoUrl)
                    .into(photoUrlImage);

            return observationView;
        }
    }

