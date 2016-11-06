package com.github.funnygopher.imhungry.ui.recyclerview.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.ui.recyclerview.viewholders.PlaceViewHolder;

import io.realm.OrderedRealmCollection;
import xyz.projectplay.realmsearchadapter.RealmSearchAdapter;

public class PlaceRecyclerAdapter extends RealmSearchAdapter<Place, PlaceViewHolder> {

    public PlaceRecyclerAdapter(@NonNull Context context, @NonNull OrderedRealmCollection<Place> data, @NonNull String filterKey) {
        super(context, data, filterKey);
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PlaceViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_places_list_item, viewGroup, false));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onBindViewHolder(PlaceViewHolder viewHolder, int i) {
        Place place = getData().get(i);
        viewHolder.bind(place);
    }
}