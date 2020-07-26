package br.com.beer.retrofit.service;

import java.util.List;

import br.com.beer.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerService {

    @GET("beers")
    Call<List<Beer>> findAll();

}
