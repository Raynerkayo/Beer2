package br.com.beer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.beer.R;
import br.com.beer.model.Beer;
import br.com.beer.ui.BeerListView;

import static br.com.beer.ui.activity.util.ConstantsActivities.KEY_BEER;

public class BeerListActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR = "Beer List";
    private BeerListView beerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);
        setTitle(TITLE_APPBAR);
        beerListView = new BeerListView(this);
        configureList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        beerListView.updateBeer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_beer_list_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_beer_list_favorite_menu) {
            openFavorites();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openFavorites() {
        startActivity(new Intent(this, BeerListFavoriteActivity.class));
    }

    private void configureList() {
        ListView beerList = findViewById(R.id.activity_beer_list_listview);
        beerListView.adapterConfigure(beerList);
        configureClickListenerItemList(beerList);
        registerForContextMenu(beerList);
    }

    private void configureClickListenerItemList(ListView beerList) {
        beerList.setOnItemClickListener(
                (adapterView, view, position, id) -> {
                    Beer beerSelected = (Beer) adapterView.getItemAtPosition(position);
                    openDetailsEditModeBeer(beerSelected);
                }
        );
    }

    private void openDetailsEditModeBeer(Beer beer) {
        Intent goToDetails = new Intent(this, BeerDetailsActivity.class);
        goToDetails.putExtra(KEY_BEER, beer);
        startActivity(goToDetails);
    }

}
