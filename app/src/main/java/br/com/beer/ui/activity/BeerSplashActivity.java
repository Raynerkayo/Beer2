package br.com.beer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import br.com.beer.R;

public class BeerSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isConnected()){
                    loadBeerListActivity();
                } else {
                    loadNoConnection();
                }
            }
        }, 1500);

    }

    private void loadNoConnection(){
        Intent intent = new Intent(BeerSplashActivity.this, BeerConnectionActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadBeerListActivity() {
        Intent intent = new Intent(BeerSplashActivity.this, BeerListActivity.class);
        startActivity(intent);
        finish();
    }

    private Boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
