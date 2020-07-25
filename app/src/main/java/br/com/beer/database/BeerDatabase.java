package br.com.beer.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.beer.database.dao.BeerDAO;
import br.com.beer.model.Beer;

@Database(entities = {Beer.class}, version = 1, exportSchema = false)
public abstract class BeerDatabase extends RoomDatabase {

    private static final String NAME_DB = "beer.db";

    public static BeerDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, BeerDatabase.class, NAME_DB)
                .allowMainThreadQueries()
                .build();
    }

    public abstract BeerDAO getRoomBeerDAO();

}
