package br.com.beer.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.beer.model.Beer;

public class BeerDAO {

    private final static List<Beer> beers = new ArrayList<>();

    public void save(Beer beer) {
        beers.add(beer);
    }

    public List<Beer> getAll() {
        return new ArrayList<>(beers);
    }
}
