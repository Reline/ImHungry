package com.github.funnygopher.imhungry.ui;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceCardViewHolder {

    @BindView(R.id.place_detail_card_title)
    TextView title;

    @BindView(R.id.place_detail_card_price)
    TextView price;

    @BindView(R.id.place_detail_card_distance)
    TextView distance;

    @BindView(R.id.place_detail_card_description)
    TextView description;

    public PlaceCardViewHolder(CardView itemView) {
        ButterKnife.bind(this, itemView);
        itemView.setVisibility(View.VISIBLE);
    }

    public void bind(final Place place) {
        title.setText(place.getName());
        price.setText(Price.getName(place.getPrice()));
        description.setText(place.getDescription());
    }
}
