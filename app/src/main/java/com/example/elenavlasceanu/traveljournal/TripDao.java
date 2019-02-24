package com.example.elenavlasceanu.traveljournal;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TripDao {
    @Query("SELECT * FROM Trips")
    List<Trip> getAllTrips();
    @Insert
    void insertTrip(Trip trip);
        @Update
    void updateTrip(Trip trip);
        @Delete
    void deleteTrip(Trip trip);
}
