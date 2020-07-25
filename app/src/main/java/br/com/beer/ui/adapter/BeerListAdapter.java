package br.com.beer.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.beer.R;
import br.com.beer.model.Beer;

public class BeerListAdapter extends BaseAdapter {

    private final List<Beer> beers = new ArrayList<>();
    private final Context context;

    public BeerListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Beer getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        //aqui vai ficar getCoLocal quando for lista de favoritos
        return beers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View inflateViewItemBeer = createView(viewGroup);
        Beer beer = beers.get(position);
        bind(inflateViewItemBeer, beer);

        return inflateViewItemBeer;
    }

    private void bind(View view, Beer beer) {
        TextView itemName = view.findViewById(R.id.item_beer_name);
        TextView itemTageline = view.findViewById(R.id.item_beer_tagline);
        ImageView itemPlaceholder = view.findViewById(R.id.item_beer_placeholder);
        ImageView itemFavorite = view.findViewById(R.id.item_beer_star);

        itemName.setText(beer.getName());
        itemTageline.setText(beer.getTagline());
        itemFavorite.setImageResource(beer.getFavorite() ? R.drawable.staron : R.drawable.staroff);
        itemPlaceholder.setImageResource(R.drawable.placeholder);
        //Picasso.get().load(beer.getImage_url()).into(itemFavorite);
        //setImageResource(cerveja.getFavorite() ? R.drawable.staron : R.drawable.staroff);
        //itemFavorite.setText(String.valueOf(beer.getFavorite()));
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_beer, viewGroup, false);
    }

    public void update(List<Beer> beers){
        this.beers.clear();
        this.beers.addAll(beers);
        notifyDataSetChanged();
    }

    public void remove(Beer beerSelected) {
        beers.remove(beerSelected);
        notifyDataSetChanged();
    }
}
