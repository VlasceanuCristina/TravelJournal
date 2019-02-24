package com.example.elenavlasceanu.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TripViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTextViewName;
    public TextView mTextViewLocation;
    public TextView mTextViewPrice;
    public ImageButton mImageButtonBookmark;
    public LinearLayout mlinearLayout;

    public ImageView getmImageView() {
        return mImageView;
    }

    public void setmImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }



    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.imageview_picturet);
        mTextViewName = itemView.findViewById(R.id.textview_namet);
        mTextViewLocation = itemView.findViewById(R.id.textview_locationt);
        mTextViewPrice = itemView.findViewById(R.id.textview_pricet);
        mImageButtonBookmark = itemView.findViewById(R.id.bookmark);
        mlinearLayout=itemView.findViewById(R.id.linearLayout);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=getAdapterPosition();
            }
        });
    }
}