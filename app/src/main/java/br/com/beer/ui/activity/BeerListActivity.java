package br.com.beer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.beer.R;

public class BeerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);
        setTitle("Beer List");
        List<String> beers = new ArrayList<>(Arrays.asList("Skol", "Devassa", "Stella", "Skol", "Devassa", "Stella", "Skol", "Devassa", "Stella", "Skol", "Devassa", "Stella", "Skol", "Devassa", "Stella", "Skol", "Devassa", "Stella"));
        ListView beerList = findViewById(R.id.activity_beer_list_listview);
        beerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, beers));
    }
}
