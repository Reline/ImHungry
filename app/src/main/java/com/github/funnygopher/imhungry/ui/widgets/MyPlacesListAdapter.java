package com.github.funnygopher.imhungry.ui.widgets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;
import com.github.funnygopher.imhungry.model.ShuffleBag;
import com.github.funnygopher.imhungry.model.database.CupboardDBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MyPlacesListAdapter extends BaseAdapter {

    private CupboardDBHelper dbHelper;
    private List<Place> places;

    public MyPlacesListAdapter(Context context) {
        dbHelper = new CupboardDBHelper(context);
        places = new ArrayList<>();
        update();
    }

    private void sortByName() {
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place place1, Place place2) {
                return place1.getName().compareTo(place2.getName());
            }
        });
    }

    public void update() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Place> newPlaces = cupboard().withDatabase(db).query(Place.class).list();
        places.clear();
        places.addAll(newPlaces);
        sortByName();
        this.notifyDataSetChanged();
    }

    public Place shufflePlaces(Place currentPlace, int price) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Place> sortedPlaces = cupboard().withDatabase(db).query(Place.class).withSelection("price = ?", Integer.toString(price)).list();

        if(sortedPlaces.size() == 0) {
            return null;
        }

        ShuffleBag<Place> shuffleBag = new ShuffleBag<>(sortedPlaces);
        if(currentPlace != null) {
            return shuffleBag.grabItemButNotThisItem(currentPlace);
        }

        return shuffleBag.grabItem();
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
        price.setText(Price.getName(place.getPrice()));

        return convertView;
    }
}
