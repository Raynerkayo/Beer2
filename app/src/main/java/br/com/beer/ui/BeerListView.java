package br.com.beer.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.beer.dao.BeerDAO;
import br.com.beer.model.Beer;
import br.com.beer.ui.adapter.BeerListAdapter;

import static br.com.beer.ui.activity.util.ConstantsActivities.MESSAGE;
import static br.com.beer.ui.activity.util.ConstantsActivities.REMOVE_BEER;

public class BeerListView {

    private final BeerListAdapter adapter;
    private final Context context;
    private final BeerDAO beerDAO;

    public BeerListView(Context context) {
        this.context = context;
        this.adapter = new BeerListAdapter(this.context);
        this.beerDAO = new BeerDAO();
    }

    public void confirmRemove(@NonNull MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle(REMOVE_BEER)
                .setMessage(MESSAGE)
                .setPositiveButton("Yes", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Beer beerItem = adapter.getItem(menuInfo.position);
                    remove(beerItem);
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void updateBeer() {
        adapter.update(beerDAO.getAll());
    }

    private void remove(Beer beerSelected) {
        beerDAO.remove(beerSelected);
        adapter.remove(beerSelected);
    }

    public void adapterConfigure(ListView beerList) {
        beerList.setAdapter(
                adapter
        );
    }

}
