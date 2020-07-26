package br.com.beer.ui.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        configureNewBeerFAB();
        configureList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        beerListView.updateBeer();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_beer_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.activity_beer_list_remove) {
            beerListView.confirmRemove(item);
        }
        return super.onContextItemSelected(item);
    }
    //aqui vai ser usado para quando clicar no botão de favorito
    private void configureNewBeerFAB() {
        FloatingActionButton fabButtonAdd = findViewById(R.id.activity_list_beer_fab_new_favorite_beer);
        fabButtonAdd.setOnClickListener(
                view -> openDetailsInsertModeBeer()
        );
    }

    private void openDetailsInsertModeBeer() {
        startActivity(new Intent(this, BeerDetailsActivity.class));
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
