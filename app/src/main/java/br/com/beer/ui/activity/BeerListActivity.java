package br.com.beer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.beer.R;
import br.com.beer.dao.BeerDAO;
import br.com.beer.model.Beer;

public class BeerListActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Beer List";
    private final BeerDAO beerDAO = new BeerDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);
        setTitle(TITLE_APPBAR);
        configureNewBeerFAB();

        //TODO remover comentário e função salvar. Save apenas para testes.
        //deve para esta activity, puxar do servidor.
        //daí usa o mesmo laytou para criar a de favoritos.
        beerDAO.save(new Beer("Name", "Tagline", "Description"));
        beerDAO.save(new Beer("Name2", "Tagline2", "Description3"));
    }

    private void configureNewBeerFAB() {
        FloatingActionButton fabButtonAdd = findViewById(R.id.activity_list_beer_fab_new_favorite_beer);
        fabButtonAdd.setOnClickListener(
                view -> {
                    openDetails();
                }
        );
    }

    private void openDetails() {
        startActivity(new Intent(this, BeerDetailsActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureList();
    }

    private void configureList() {
        ListView beerList = findViewById(R.id.activity_beer_list_listview);
        beerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, beerDAO.getAll()));
        beerList.setOnItemClickListener(
                (adapterView, view, position, id) -> {
                    Log.i("position in list", "configureList: " + position);
                    Toast.makeText(this, "Clique", Toast.LENGTH_LONG).show();
                }
        );
    }
}
