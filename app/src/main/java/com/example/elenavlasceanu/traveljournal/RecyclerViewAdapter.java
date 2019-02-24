package com.example.elenavlasceanu.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {
    @NonNull
    private int [] images;
    public RecyclerViewAdapter (int[] images){
        this.images = images;
    }
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album, viewGroup, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {
     int image_id= images[i];
        viewHolder.Album.setImageResource(image_id);
        viewHolder.AlbumTitle.setText("Image:" + i);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView Album;
        TextView AlbumTitle;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Album = itemView.findViewById(R.id.album);
            AlbumTitle = itemView.findViewById(R.id.album_title);
        }
    }
}
