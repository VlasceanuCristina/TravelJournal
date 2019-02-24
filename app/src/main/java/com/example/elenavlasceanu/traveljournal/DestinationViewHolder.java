package com.example.elenavlasceanu.traveljournal;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DestinationViewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;
    public TextView mTextViewName;
    public TextView mTextViewLocation;


    public DestinationViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.imageview_picture);
        mTextViewName = itemView.findViewById(R.id.textview_name);
        mTextViewLocation = itemView.findViewById(R.id.textview_location);
    }
}
