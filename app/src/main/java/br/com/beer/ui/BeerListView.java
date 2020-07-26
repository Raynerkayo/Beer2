package br.com.beer.ui;

import android.content.Context;
import android.widget.ListView;

import br.com.beer.database.BeerDatabase;
import br.com.beer.database.dao.BeerDAO;
import br.com.beer.repository.BeerRepository;
import br.com.beer.ui.adapter.BeerListAdapter;

public class BeerListView {

    private final BeerListAdapter adapter;
    private final Context context;
    private final BeerDAO beerDAO;
    private BeerRepository repository;

    public BeerListView(Context context) {
        this.context = context;
        this.adapter = new BeerListAdapter(this.context);
        beerDAO = BeerDatabase.getInstance(this.context).getRoomBeerDAO();
        repository = new BeerRepository(beerDAO);
    }

    public void updateBeer() {
        repository.getBeers(adapter::update);
    }

    public void adapterConfigure(ListView beerList) {
        beerList.setAdapter(
                adapter
        );
    }

    public void updateFavoriteBeer() {

        repository.getBeerFavorites(adapter::update);

    }

    public void search(String newText) {

        adapter.getFilter().filter(newText);

    }
}
