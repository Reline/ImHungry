package com.github.funnygopher.imhungry.ui.recyclerview.viewholders;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;

public class PlaceViewHolder extends RealmSearchViewHolder {

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
                final Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.place_detail_cardview);

                TextView title = (TextView) dialog.findViewById(R.id.place_detail_card_title);
                TextView priceDistance = (TextView) dialog.findViewById(R.id.place_detail_card_price_distance);
                TextView description = (TextView) dialog.findViewById(R.id.place_detail_card_description);

                title.setText(place.getName());
                priceDistance.setText(Price.getName(place.getPrice()) + " - " + "2.0 mi");
                description.setText(place.getDescription());

                dialog.show();

                Window window = dialog.getWindow();
                if (window != null) {
                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
            }
        });
    }
}
