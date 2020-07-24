package br.com.beer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.beer.R;
import br.com.beer.dao.BeerDAO;
import br.com.beer.model.Beer;

public class BeerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);

        BeerDAO beerDAO = new BeerDAO();

        final EditText textViewTitle = findViewById(R.id.activity_beer_details_title);
        final EditText textViewSubTitle = findViewById(R.id.activity_beer_details_sub_title);
        final EditText textViewContent = findViewById(R.id.activity_beer_details_content);

        Button buttonSave = findViewById(R.id.activity_beer_details_button_save);
        buttonSave.setOnClickListener(
                view -> {
                    String title = textViewTitle.getText().toString();
                    String subTitle = textViewSubTitle.getText().toString();
                    String content = textViewContent.getText().toString();

                    Beer beer = new Beer(title, subTitle, content);
                    beerDAO.save(beer);

                    finish();

                }
        );

    }
}
