package br.com.beer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.beer.R;
import br.com.beer.dao.BeerDAO;
import br.com.beer.model.Beer;

import static br.com.beer.ui.activity.util.ConstantsActivities.KEY_BEER;

public class BeerDetailsActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR = "Beer Details";
    private final BeerDAO beerDAO = new BeerDAO();
    private EditText editTextName;
    private EditText editTextTagline;
    private EditText editTextDescription;
    private Beer beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        setTitle(TITLE_APPBAR);
        fieldsInitializer();
        loadBeer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_beer_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //será se posso colocar um botão para editar e liberar os campos, aqui?
    //ou, posso cirar um novodetails, para editar. se ele clicar no botão de editar
    //vai para a outra view ou activity; se for, finaliza essa.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.activity_beer_details_menu_save){
            endDetails();
        }
        //fazer um else if para o comentário acima.
        return super.onOptionsItemSelected(item);
    }

    private void loadBeer() {
        Intent data = getIntent();

        if (data.hasExtra(KEY_BEER)) {
            beer = (Beer) data.getSerializableExtra(KEY_BEER);
            fillBearEditText();
        } else {
            beer = new Beer();
        }
    }

    private void fillBearEditText() {
        editTextName.setText(beer.getName());
        editTextTagline.setText(beer.getTagline());
        editTextDescription.setText(beer.getDescription());
    }

    private void endDetails() {
        fillBeer();
        if (beer.isValidCod()) {
            beerDAO.edit(beer);
        } else {
            beerDAO.save(beer);
        }
        finish();
    }

    private void fieldsInitializer() {
        editTextName = findViewById(R.id.activity_beer_details_name);
        editTextTagline = findViewById(R.id.activity_beer_details_tagline);
        editTextDescription = findViewById(R.id.activity_beer_details_description);
    }

    private void fillBeer() {
        String name = editTextName.getText().toString();
        String tagline = editTextTagline.getText().toString();
        String description = editTextDescription.getText().toString();

        beer.setName(name);
        beer.setTagline(tagline);
        beer.setDescription(description);
    }
}
