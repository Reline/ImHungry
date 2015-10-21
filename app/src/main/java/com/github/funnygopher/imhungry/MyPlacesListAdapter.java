package com.github.funnygopher.imhungry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyPlacesListAdapter extends BaseAdapter {

    private List<Place> places;

    public MyPlacesListAdapter(List<Place> places) {
        this.places = places;

        List<Place> morePlaces = new ArrayList<>();
        Place place1 = new Place("WichWich", "A good place for sandwiches!", Place.Price.KIND_OF_CHEAP, "The Address", true);
        Place place2 = new Place("Wendy's", "It's a Wendy's...", Place.Price.CHEAP, "The Address", false);
        Place place3 = new Place("Jimmy Johns", "Good cheap sandwiches", Place.Price.CHEAP, "The Address", false);
        Place place4 = new Place("Shobu Fondue", "Shobu shobu!! Swish swish!!", Place.Price.EXPENSIVE, "The Address", true);

        for(int i = 1; i < 19; i++) {
            if(i % 4 == 0) {
                morePlaces.add(place1);
            }
            if(i % 4 == 1) {
                morePlaces.add(place2);
            }
            if(i % 4 == 2) {
                morePlaces.add(place3);
            }
            if(i % 4 == 3) {
                morePlaces.add(place4);
            }
        }
        for(Place place : morePlaces) {
            places.add(place);
        }
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Place getItem(int position) {
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_places_list_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.my_places_list_item_title);
        TextView description = (TextView) convertView.findViewById(R.id.my_places_list_item_desc);
        TextView price = (TextView) convertView.findViewById(R.id.my_places_list_item_price);

        Place place = getItem(position);
        name.setText(place.getName());
        description.setText(place.getDescription());
        price.setText(place.getPrice().toString());

        return convertView;
    }
}
