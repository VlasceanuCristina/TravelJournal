package com.example.elenavlasceanu.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class DestionationRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewDestinations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destionation_recycler_view);
    }
    private void initView() {
        mRecyclerViewDestinations = findViewById(R.id.recyclerview_destinations);

        //set the layout manager for the current recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewDestinations.setLayoutManager(layoutManager);

        //create the adapter
        DestinationAdapter destinationAdapter = new DestinationAdapter(getDestinationsList());

        //set the adapter to the recycler view
        mRecyclerViewDestinations.setAdapter(destinationAdapter);
    }

    //get the data source
    private List<Destination> getDestinationsList() {
        List<Destination> destinations = new ArrayList<>();
        Destination dest1 = new Destination("Holiday 2017", "Islands",
                "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60");
        Destination dest2 = new Destination("Fall 2017", "Rome",
                "https://unsplash.com/photos/YpKiwlvhOpI");
        Destination dest3 = new Destination("Summer 2017", "London",
                "https://unsplash.com/photos/g-krQzQo9mI");
        Destination dest4 = new Destination("Winter 2017", "Paris",
                "https://unsplash.com/photos/nnzkZNYWHaU");
        Destination dest5 = new Destination("Spring 2018", "San Francoisco",
                "https://unsplash.com/photos/Ji_G7Bu1MoM");
        Destination dest6 = new Destination("Summer 2018", "Moscova",
                "https://unsplash.com/photos/vFmq8mpNWqw");
        destinations.add(dest1);
        destinations.add(dest2);
        destinations.add(dest3);
        destinations.add(dest4);
        destinations.add(dest5);
        destinations.add(dest6);

        return destinations;
    }
    public void btnAddTripOnClick(View view){
        Intent intent = new Intent(this, Manage_Trip.class);
        startActivity(intent);
    }
}
