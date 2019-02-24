package com.example.elenavlasceanu.traveljournal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewImage;
    private RecyclerViewAdapter adapter;
    private int [] images={R.drawable.franciso, R.drawable.londra, R.drawable.rmoscova, R.drawable.fparis, R.drawable.oras,R.drawable.islanda };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        mRecyclerViewImage=findViewById(R.id.recyclerview_image);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewImage.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(images);
        mRecyclerViewImage.setAdapter(adapter);
    }

}
