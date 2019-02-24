package com.example.elenavlasceanu.traveljournal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRecyclerViewActivity extends Fragment {
    private List<Trip> mTrips= new ArrayList<>();
    //@Override
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_recycler_view);
        initView();

    }*/

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Task mQuery=db.collection("trips")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Trip trip=  (Trip)document.getData();
                            //mTrips.add(trip);
                            String nume=document.getString("mName");
                            String location=document.getString("mLocation");
                            String picture=document.getString("mPicture");
                            double price=document.getDouble("mPrice");
                            Trip trip=new Trip(nume,location,picture,price,true);
                            mTrips.add(trip);


                        }
                    }
                }
            });

 public static TripRecyclerViewActivity newInstance(){
   TripRecyclerViewActivity tripRecyclerViewActivity =new TripRecyclerViewActivity();
   return tripRecyclerViewActivity;
 }
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
  View rootView=inflater.inflate(R.layout.activity_trip_recycler_view,null);
 RecyclerView rv=(RecyclerView) rootView.findViewById(R.id.recyclerview_trips);
rv.setLayoutManager(new LinearLayoutManager(getActivity()));
rv.setAdapter(new TripAdapter(mTrips,getActivity()));
return rootView;

}
public String toString(){
        return "TripRecyclerViewActivity";
}



   // private RecyclerView mRecyclerViewTrips;

   /* private void initView() {
       //mRecyclerViewTrips = findViewById(R.id.recyclerview_trips);

        //set the layout manager for the current recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewTrips.setLayoutManager(layoutManager);


        TripAdapter tripAdapter = new TripAdapter(getTripsList(), TripRecyclerViewActivity.this);
        mRecyclerViewTrips.setAdapter(tripAdapter);

        mRecyclerViewTrips.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecyclerViewTrips, new ContactClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(TripRecyclerViewActivity.this, getString(R.string.single_click),
                        Toast.LENGTH_SHORT).show();

                // TODO button click inside of recycler view
                ImageButton button = view.findViewById(R.id.bookmark);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // implement button click
                        Toast.makeText(TripRecyclerViewActivity.this,  getString(R.string.button_item),
                                Toast.LENGTH_SHORT).show();
                        /// partea cu bookmark/////
                       /*boolean isFavourite = readState();
                        final ImageButton imgButton = (ImageButton) findViewById(R.id.bookmark);
                        if (isFavourite) {
                            imgButton.setBackgroundResource(R.drawable.ic_bookmark);
                            isFavourite = false;
                            saveState(isFavourite);

                        } else {
                            imgButton.setBackgroundResource(R.drawable.ic_bookmark_plin);
                            isFavourite = true;
                            saveState(isFavourite);
                        }*/

/*
                    }
                });

            }
            @Override
            public void onLongClick(View view, int position) {

                Toast.makeText(TripRecyclerViewActivity.this, getString(R.string.long_click) + position,
                        Toast.LENGTH_LONG).show();
                //Intent intent= new Intent(TripRecyclerViewActivity.this,DisplayDetailsTrip.class);
                /*Trip tripItem=mTrips.get(position);
                intent.putExtra("name",tripItem.getmName());
                intent.putExtra("location",tripItem.getmLocation());
                intent.putExtra("picture",tripItem.getmPicture());
                intent.putExtra("price",tripItem.getmPrice());
                intent.putExtra("bookmark",tripItem.getmBookmark());*/

               // startActivity(intent);

/*
            }
        }));


    }*/
    //get the data source
    private List<Trip> getTripsList() {
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip("Holiday 2017", "Islands",
                "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",500,true);
        Trip trip2 = new Trip("Fall 2017", "Rome",
                "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",600,true);
        Trip trip3 = new Trip("Summer 2017", "London",
                "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",1000,true);
        Trip trip4 = new Trip("Winter 2017", "Paris",
                "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",1500,true);
        Trip trip5 = new Trip("Spring 2018", "San Francoisco",
                "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",1200,true);
        Trip trip6 = new Trip("Summer 2018", "Moscova",
                "https://firebasestorage.googleapis.com/v0/b/traveljournalfirebase.appspot.com/o/trips%2F1550993801485.jpg?alt=media&token=84e24cf3-a50b-40da-ba2a-1b90fec074d4",1300,true);
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        trips.add(trip5);

        trips.add(trip6);
        return trips;

    }
    public void btnAddTripClick(View view) {
        Intent intent = new Intent(getActivity(), Manage_Trip.class);
        startActivity(intent);
    }




}
