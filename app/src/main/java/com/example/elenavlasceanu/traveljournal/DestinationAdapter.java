package com.example.elenavlasceanu.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

//import com.squareup.picasso.Picasso;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationViewHolder> {
private List<Destination> mDestinations;

public DestinationAdapter(List<Destination> destinations) {
        mDestinations = destinations;
        }

@NonNull
@Override
public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View destinationView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.destination_item, viewGroup, false);
        return new DestinationViewHolder(destinationView);
        }

@Override
public void onBindViewHolder(@NonNull DestinationViewHolder destinationViewHolder, int i) {
        Destination destination = mDestinations.get(i);
        destinationViewHolder.mTextViewName.setText(destination.getName());
        destinationViewHolder.mTextViewLocation.setText(destination.getLocation());
       // Picasso.get().load(destination.getPicture()).into(destinationViewHolder.mImageView);
        }

@Override
public int getItemCount() {
        return mDestinations.size();
        }
}
