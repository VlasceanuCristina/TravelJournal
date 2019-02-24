package com.example.elenavlasceanu.traveljournal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {
    private List<Trip> mTrips;
    private Context mContext;

    public  TripAdapter(List<Trip> trips, Context context) {
        mTrips = trips;
        mContext = context;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_item
                , viewGroup, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TripViewHolder tripViewHolder, int i) {
        final Trip currentTrip = mTrips.get(i);
        tripViewHolder.mTextViewName.setText(currentTrip.getmName());
        tripViewHolder.mTextViewLocation.setText(currentTrip.getmLocation());
        tripViewHolder.mTextViewPrice.setText(currentTrip.getmPrice()+ " EUR");
        Boolean isFavourite=currentTrip.getmBookmark();
        Picasso.get().load(currentTrip.getmPicture())
                .into(tripViewHolder.mImageView);

        tripViewHolder.mImageButtonBookmark.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       uploadTrip(currentTrip);
                                                                       Toast.makeText(mContext, "Trip added to favourite",
                                                                               Toast.LENGTH_SHORT).show();
                                                                       tripViewHolder.mImageButtonBookmark.setBackgroundResource(R.drawable.ic_bookmark_plin);
                                                                   }
                                                               });
                tripViewHolder.mlinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(mContext, DisplayDetailsTrip.class);
                        intent.putExtra("name", currentTrip.getmName());
                        intent.putExtra("location", currentTrip.getmLocation());
                        intent.putExtra("picture", currentTrip.getmPicture());
                        intent.putExtra("price", currentTrip.getmPrice());
                        intent.putExtra("bookmark", currentTrip.getmBookmark());
                        mContext.startActivity(intent);

                    }
                });

        tripViewHolder.mlinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(mContext,"Edit trip" , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,Manage_Trip.class);
                mContext.startActivity(intent);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }
    public void uploadTrip(Trip upload){
        FirebaseFirestore.getInstance().collection("favourite").add(upload);
    }
}
