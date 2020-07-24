package br.com.beer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.beer.R;
import br.com.beer.dao.BeerDAO;

public class BeerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);
        setTitle("Beer List");

        FloatingActionButton fabButtonAdd = findViewById(R.id.activity_list_beer_fab_new_favorite_beer);
        fabButtonAdd.setOnClickListener(
                view -> {
                    startActivity(new Intent(this, BeerDetailsActivity.class));
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        BeerDAO beerDAO = new BeerDAO();

        ListView beerList = findViewById(R.id.activity_beer_list_listview);
        beerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, beerDAO.getAll()));
    }
}
