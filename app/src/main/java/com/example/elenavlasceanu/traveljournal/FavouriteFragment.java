package com.example.elenavlasceanu.traveljournal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment  extends Fragment{
    private List<Trip> mTrips= new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Task mQuery=db.collection("favourite")
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

    public static FavouriteFragment newInstance(){
        FavouriteFragment favouriteFragment=new FavouriteFragment();
        return favouriteFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView=inflater.inflate(R.layout.favourite_fragment,null);
        RecyclerView rv=(RecyclerView) rootView.findViewById(R.id.recyclerview_trips_favourite);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new TripAdapter(mTrips,getActivity()));
        return rootView;

    }
    public String toString(){
        return "FavouriteFragment";
    }


}