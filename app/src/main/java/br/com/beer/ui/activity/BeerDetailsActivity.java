package br.com.beer.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.beer.R;
import br.com.beer.dao.BeerDAO;
import br.com.beer.model.Beer;

public class BeerDetailsActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Beer Details";
    private EditText textViewName;
    private EditText textViewTagline;
    private EditText textViewDescription;

    private final BeerDAO beerDAO = new BeerDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        setTitle(TITLE_APPBAR);
        fieldsInitializer();
        configureSaveButton();
    }

    private void configureSaveButton() {
        Button buttonSave = findViewById(R.id.activity_beer_details_button_save);
        buttonSave.setOnClickListener(
                view -> {
                    Beer beer = createBeer();
                    save(beer);
                }
        );
    }

    private void fieldsInitializer() {
        textViewName = findViewById(R.id.activity_beer_details_name);
        textViewTagline = findViewById(R.id.activity_beer_details_tagline);
        textViewDescription = findViewById(R.id.activity_beer_details_description);
    }

    private void save(Beer beer) {
        beerDAO.save(beer);
        finish();
    }

    private Beer createBeer() {
        String name = textViewName.getText().toString();
        String tagline = textViewTagline.getText().toString();
        String description = textViewDescription.getText().toString();

        return new Beer(name, tagline, description);
    }
}
