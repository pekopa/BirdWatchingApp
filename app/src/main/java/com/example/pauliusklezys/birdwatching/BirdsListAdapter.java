package com.example.pauliusklezys.birdwatching;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class BirdsListAdapter extends AppCompatActivity {

    public class BirdList extends ArrayAdapter<Bird> {
        private final int resource;

        public BirdList(Context context, int resource, List<Bird> objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        public BirdList(Context context, int resource, Bird[] objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            Bird bird = getItem(position);
            /*String picture = bird.getPhotoUrl();*/
            LinearLayout birdView;
            if (convertView == null) {
                birdView = new LinearLayout(getContext());
                String inflater = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
                li.inflate(resource, birdView, true);
            } else {
                birdView = (LinearLayout) convertView;
            }
            return birdView;
        }
    }
}
