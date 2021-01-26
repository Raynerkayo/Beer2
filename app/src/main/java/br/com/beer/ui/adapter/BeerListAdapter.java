package br.com.beer.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.beer.R;
import br.com.beer.database.BeerDatabase;
import br.com.beer.database.dao.BeerDAO;
import br.com.beer.model.Beer;

public class BeerListAdapter extends BaseAdapter implements Filterable {

    private final List<Beer> beers = new ArrayList<>();
    private final List<Beer> beersAll;
    private final Context context;

    public BeerListAdapter(Context context) {
        this.context = context;
        this.beersAll = new ArrayList<>(beers);
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
        return beers.get(position).getIdLocal();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View inflateViewItemBeer = createView(viewGroup);
        Beer beer = beers.get(position);
        bind(inflateViewItemBeer, beer);

        return inflateViewItemBeer;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Beer> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()){
                filteredList.addAll(beersAll);
            } else {
                for (Beer b : beersAll){
                    if (b.getName().contains(constraint.toString()))
                        filteredList.add(b);
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            beersAll.clear();
            beersAll.addAll((Collection<? extends Beer>) results.values);
            notifyDataSetChanged();
        }
    };

    private void bind(View view, Beer beer) {
        TextView itemName = view.findViewById(R.id.item_beer_name);
        TextView itemTageline = view.findViewById(R.id.item_beer_tagline);
        ImageView itemPlaceholder = view.findViewById(R.id.item_beer_placeholder);
        ImageView itemFavorite = view.findViewById(R.id.item_beer_star);

        itemName.setText(beer.getName());
        itemTageline.setText(beer.getTagline());
        itemFavorite.setImageResource(beer.getFavorite() ? R.drawable.staron : R.drawable.staroff);
        itemFavorite.setOnClickListener(
                s -> favorite(beer, itemFavorite)
        );
        itemPlaceholder.setImageResource(R.drawable.placeholder);
        Picasso.get().load(beer.getImage_url()).into(itemPlaceholder);
    }

    private void favorite(Beer beer, ImageView itemFavorite) {
        BeerDAO beerDAO = BeerDatabase.getInstance(this.context).getRoomBeerDAO();
        if (beer != null) {
            if (beerDAO.find(beer.getIdApi()) == null){
                notifyDataSetChanged();
                beerDAO.save(beer);
                beer.setFavorite(true);
                itemFavorite.setImageResource(R.drawable.staron);
                notifyDataSetChanged();
            } else {
                notifyDataSetChanged();
                beerDAO.remove(beer);
                beer.setFavorite(false);
                itemFavorite.setImageResource(R.drawable.staroff);
                notifyDataSetChanged();
            }
        }

        ;
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item, viewGroup, false);
    }

    public void update(List<Beer> beers) {
        notifyDataSetChanged();
        this.beers.clear();
        this.beers.addAll(beers);
        notifyDataSetChanged();
    }

    public void remove(Beer beerSelected) {
        beers.remove(beerSelected);
        notifyDataSetChanged();
    }

}
