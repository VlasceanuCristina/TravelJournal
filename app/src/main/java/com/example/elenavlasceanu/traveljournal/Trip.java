package com.example.elenavlasceanu.traveljournal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

@Entity(tableName = "Trips")
public class Trip {
    @ColumnInfo(name="name")
    public String mName; ///am facut membri publici pentru ca imi dadea eroare de la room
    @NonNull
    public String getmName() {
        return mName;
    }
    public void setmName(@NonNull String mName) {
        this.mName = mName;
    }


    @ColumnInfo(name="location")
    public String mLocation;
    public String getmLocation() {
        return mLocation;
    }
    public void setmLocation(@NonNull String mLocation) {
        this.mLocation = mLocation;
    }

    @ColumnInfo(name="picture")
    public String mPicture;
    public String getmPicture() {
        return mPicture;
    }
    public void setmPicture(@NonNull String mPicture) {
        this.mPicture = mPicture;
    }


    @ColumnInfo(name="bookmark")
    public Boolean mBookmark;
    public Boolean getmBookmark() {
        return mBookmark;
    }

    public void setmBookmark(@NonNull Boolean mBookmark) {
        this.mBookmark = mBookmark;
    }

   @ColumnInfo(name="price")
    public  double mPrice;
    public double getmPrice() {
        return mPrice;
    }
    public void setmPrice(@NonNull double mPrice) {
        this.mPrice = mPrice;
    }


   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name ="id")
   public int tripId;
    public  int getTripId() {
        return tripId;
    }

    public void setTripId(@NonNull int tripId) {
        this.tripId = tripId;
    }


    public String getTripFirebaseId() {
        return tripFirebaseId;
    }

    public void setTripFirebaseId(String tripFirebaseId) {
        this.tripFirebaseId = tripFirebaseId;
    }
    @Exclude
    private String tripFirebaseId;


    public Trip(String name, String location,String picture, double price,Boolean bookmark) {

        mName = name;
        mLocation = location;
        mPicture = picture;
        mPrice=price;
        mBookmark=bookmark;
    }
}
