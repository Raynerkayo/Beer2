package br.com.beer;

import android.app.Application;

import br.com.beer.dao.BeerDAO;
import br.com.beer.model.Beer;

/**
 * Classe para resolver o bug de rotação duplicar conteudo de teste.
 * */
public class BeerAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createBeerTest();
    }

    private void createBeerTest() {
        BeerDAO beerDAO = new BeerDAO();
        beerDAO.save(new Beer("Name", "Tagline", "Description"));
        beerDAO.save(new Beer("Name2", "Tagline2", "Description2"));
    }
}
