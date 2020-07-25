package br.com.beer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.beer.R;
import br.com.beer.ui.BeerListView;

public class BeerPunkActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR = "Beer List";
    private BeerListView beerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_punk);
    }
}
