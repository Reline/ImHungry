package com.github.funnygopher.imhungry.ui.recyclerview.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;
import com.github.funnygopher.imhungry.ui.widgets.PlaceDetailDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.my_places_list_item_title)
    TextView name;

    @BindView(R.id.my_places_list_item_desc)
    TextView description;

    @BindView(R.id.my_places_list_item_price)
    TextView price;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Place place) {
        name.setText(place.getName());
        description.setText(place.getDescription());
        price.setText(Price.getName(place.getPrice()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlaceDetailDialog(v.getContext())
                        .bind(place)
                        .show();
            }
        });
    }
}
