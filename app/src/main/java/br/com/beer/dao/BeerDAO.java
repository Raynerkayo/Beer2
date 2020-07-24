package br.com.beer.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.beer.model.Beer;

public class BeerDAO {

    private final static List<Beer> beers = new ArrayList<>();
    private static int counterCodLocal = 1;

    public void save(Beer beer) {
        beer.setCodLocal(counterCodLocal);
        beers.add(beer);
        updateLocalCode();
    }

    private void updateLocalCode() {
        counterCodLocal++;
    }

    public void edit(Beer beer) {
        Beer beerFind = findById(beer);
        if (beerFind != null) {
            int positionBeer = beers.indexOf(beerFind);
            beers.set(positionBeer, beer);
        }
    }

    private Beer findById(Beer beer) {
        for (Beer b : beers) {
            if (b.getCodLocal() == beer.getCodLocal()) {
                return b;
            }
        }
        return null;
    }

    public List<Beer> getAll() {
        return new ArrayList<>(beers);
    }

    public void remove(Beer beer) {
        if(findById(beer) != null){
            beers.remove(beer);
        }

    }
}
