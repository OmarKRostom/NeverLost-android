package com.borio;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed Emad on 4 May, 2015.
 */

public class ProvidersAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> providers;
    private final ArrayList<Integer> imageId;
    private final ArrayList<String> itemsAll;
    private final ArrayList<String> suggestions;

    private final Filter providerFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return (String) resultValue;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (String provider : itemsAll) {
                    if (provider.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(provider);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                ArrayList<String> filteredList = (ArrayList<String>) results.values;
                clear();
                for (String provider : filteredList) {
                    add(provider);
                }
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    };

    public ProvidersAdapter(Activity context, ArrayList<String> providers, ArrayList<Integer> imageId) {
        super(context, R.layout.provider_autocomplete, providers);
        this.context = context;
        this.providers = providers;
        this.imageId = imageId;
        this.itemsAll = (ArrayList<String>) providers.clone();
        this.suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.provider_autocomplete, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.provider);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);

        txtTitle.setText(providers.get(position));
        imageView.setImageResource(imageId.get(itemsAll.indexOf(providers.get(position))));

        return rowView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return providerFilter;
    }
}
