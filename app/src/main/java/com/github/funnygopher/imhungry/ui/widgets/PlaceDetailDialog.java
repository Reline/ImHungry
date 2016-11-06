package com.github.funnygopher.imhungry.ui.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailDialog extends Dialog {

    @BindView(R.id.place_detail_card_title)
    TextView title;

    @BindView(R.id.place_detail_card_price)
    TextView price;

    @BindView(R.id.place_detail_card_distance)
    TextView distance;

    @BindView(R.id.place_detail_card_description)
    TextView description;

    public PlaceDetailDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.place_detail_cardview);
        ButterKnife.bind(this);
    }

    public PlaceDetailDialog bind(Place place) {
        title.setText(place.getName());
        price.setText(Price.getName(place.getPrice()));
        description.setText(place.getDescription());
        return this;
    }

    @Override
    public void show() {
        super.show();
        if (getWindow() != null) {
            getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }
}
