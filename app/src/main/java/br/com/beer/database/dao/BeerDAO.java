package br.com.beer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.beer.model.Beer;

@Dao
public interface BeerDAO {

    @Insert
    void save(Beer beer);

    @Query("SELECT * FROM beer")
    List<Beer> getAll();

    @Delete
    void remove(Beer beerSelected);

    @Update
    void edit(Beer beer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<Beer> beersResponse);
}
