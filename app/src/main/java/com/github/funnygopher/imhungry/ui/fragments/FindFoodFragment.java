package com.github.funnygopher.imhungry.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Distance;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;
import com.github.funnygopher.imhungry.model.database.RealmService;
import com.github.funnygopher.imhungry.ui.widgets.Slider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindFoodFragment extends Fragment {
    
    RealmService realmService;

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

        realmService = new RealmService();

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
        realmService.closeRealm();
    }

    @OnClick(R.id.find_food_button)
    void onFindFoodButtonClick() {
        Place place = realmService.getRandomPlace(Price.getValue(mPriceSlider.getIndex()));
        // Couldn't find a random place...
        if (place == null) {
            Toast.makeText(getActivity(), "There is no " + Price.getName(mPriceSlider.getIndex()).toLowerCase() + " place!", Toast.LENGTH_SHORT).show();
            return;
        } else if (currentPlace != null && place.getId() == currentPlace.getId()) {
            Toast.makeText(getActivity(), "This is the only " + Price.getName(mPriceSlider.getIndex()).toLowerCase() + " place!", Toast.LENGTH_SHORT).show();
            return;
        }

        currentPlace = place;

        TextView title = (TextView) mPlaceDetail.findViewById(R.id.place_detail_card_title);
        TextView priceDistance = (TextView) mPlaceDetail.findViewById(R.id.place_detail_card_price_distance);
        TextView description = (TextView) mPlaceDetail.findViewById(R.id.place_detail_card_description);

        title.setText(place.getName());
        priceDistance.setText(Price.getName(mPriceSlider.getIndex()) + " - " + "2.0 mi");
        description.setText(place.getDescription());

        mPlaceDetail.setVisibility(View.VISIBLE);
    }
}
