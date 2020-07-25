package br.com.beer.repository;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import br.com.beer.BaseAsyncTask;
import br.com.beer.database.dao.BeerDAO;
import br.com.beer.model.Beer;
import br.com.beer.retrofit.BeerRetrofit;
import br.com.beer.retrofit.service.BeerService;
import retrofit2.Call;
import retrofit2.Response;

public class BeerRepository {

    private final BeerDAO beerDAO;

    public BeerRepository(BeerDAO dao) {
        this.beerDAO = dao;
    }

    public void getBeers(BeerLoadedListener listener) {
        getBeerInDBLocal(listener);
    }

    private void getBeerInDBLocal(BeerLoadedListener listener) {
        new BaseAsyncTask<>(beerDAO::getAll,
                result -> {
                    listener.loaded(result);
                    getBeerInAPI(listener);
                })
                .execute();
    }

    private void getBeerInAPI(BeerLoadedListener listener) {
        BeerService service = new BeerRetrofit().getBeerService();
        Call<List<Beer>> call = service.findAll();
        new BaseAsyncTask<>(() -> {
            try {
                Response<List<Beer>> response = call.execute();
                List<Beer> beerReponse = response.body();
                beerDAO.save(beerReponse);
                return beerDAO.getAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return beerDAO.getAll();
        }, listener::loaded)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public interface BeerLoadedListener {
        void loaded(List<Beer> beers);
    }

}
