package com.example.elenavlasceanu.traveljournal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Trip.class,version = 1,exportSchema = false)
public abstract  class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;
    public abstract TripDao tripDao();
    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE==null){
          INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"trip-db").build();
        }
        return INSTANCE;
    }
}
