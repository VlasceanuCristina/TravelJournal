package com.example.elenavlasceanu.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayDetailsTrip extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_details_trip);
        Intent intent = getIntent();
        String name=intent.getStringExtra("name");
        String location=intent.getStringExtra("location");
        String price=intent.getStringExtra("price");
        String picture =intent.getStringExtra("picture");
        String bookmark=intent.getStringExtra("bookmark");
        ImageView imageView =findViewById(R.id.imageview_pictured);
        ImageButton imageButton=findViewById(R.id.bookmarkd);
        TextView textViewName=findViewById(R.id.textview_named);
        TextView textViewLocation=findViewById(R.id.textview_locationd);
        TextView textViewPrice=findViewById(R.id.textview_priced);
        Picasso.get().load(picture)
                .into(imageView);
        textViewName.setText(name);
        textViewLocation.setText(location);
        textViewPrice.setText(price);
    }


}