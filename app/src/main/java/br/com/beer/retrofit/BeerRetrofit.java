package br.com.beer.retrofit;

import br.com.beer.retrofit.service.BeerService;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Data
public class BeerRetrofit {

    private final BeerService beerService;

    public BeerRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit =
                new Retrofit
                        .Builder()
                        .baseUrl("https://api.punkapi.com/v2/")
                        .client(client)
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

        beerService = retrofit.create(BeerService.class);
    }

/*
    public BeerService getBeerService() {
        return beerService;
    }
*/
}
