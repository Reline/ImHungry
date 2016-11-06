package com.github.funnygopher.imhungry.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Distance;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;
import com.github.funnygopher.imhungry.model.database.DatabaseAccessObject;
import com.github.funnygopher.imhungry.model.database.RealmAccessObject;
import com.github.funnygopher.imhungry.ui.PlaceCardViewHolder;
import com.github.funnygopher.imhungry.ui.widgets.Slider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindFoodFragment extends Fragment {
    
    DatabaseAccessObject dao;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.find_food_price_slider)
    Slider mPriceSlider;
    
    @BindView(R.id.find_food_price_textview)
    TextView mPriceText;

    @BindView(R.id.find_food_distance_slider)
    Slider mDistanceSlider;
    
    @BindView(R.id.find_food_distance_textview)
    TextView mDistanceText;

    @BindView(R.id.find_food_place_detail)
    CardView mPlaceDetail;

    Place currentPlace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_food, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        dao = new RealmAccessObject();

        // The price slider and textview
        mPriceSlider.setOnSliderChangeListener(new Slider.OnSliderChangeListener() {
            @Override
            public void onSliderChange(Slider slider, int index, int prevIndex) {
                mPriceText.setText(Price.getName(index));
            }
        });

        // The distance slider and textview
        mDistanceSlider.setOnSliderChangeListener(new Slider.OnSliderChangeListener() {
            @Override
            public void onSliderChange(Slider slider, int index, int prevIndex) {
                mDistanceText.setText(Distance.values()[index].getReadableString());
            }
        });

        mPriceText.setText(Price.getName(mPriceSlider.getIndex()));
        mDistanceText.setText(Distance.values()[mDistanceSlider.getIndex()].getReadableString());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    @OnClick(R.id.find_food_button)
    void onFindFoodButtonClick() {
        Place place = dao.getRandomPlace(Price.getValue(mPriceSlider.getIndex()));
        // Couldn't find a random place...
        String price = Price.getName(mPriceSlider.getIndex());
        String message = price.toLowerCase() + " place";
        if (place == null) {
            Snackbar.make(coordinatorLayout,
                    "There is no " + message,
                    Snackbar.LENGTH_SHORT)
                    .setAction("dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // no-op
                        }
                    })
                    .show();
            return;
        } else if (currentPlace != null && place.getId() == currentPlace.getId()) {
            Snackbar.make(coordinatorLayout,
                    "This is the only " + message,
                    Snackbar.LENGTH_SHORT)
                    .setAction("dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // no-op
                        }
                    })
                    .show();
            return;
        }

        currentPlace = place;

        PlaceCardViewHolder holder = new PlaceCardViewHolder(mPlaceDetail);
        holder.bind(place);
    }
}
