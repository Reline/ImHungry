package com.github.funnygopher.imhungry;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FindFoodFragment extends Fragment {

    private Slider mPriceSlider;
    private TextView mPriceText;

    private Slider mDistanceSlider;
    private TextView mDistanceText;

    private Button mButton;

    private CardView mPlaceDetail;
    private boolean invisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_food, container, false);

        mPriceSlider = (Slider) view.findViewById(R.id.find_food_price_slider);
        mPriceSlider.setOnSliderChangeListener(new Slider.OnSliderChangeListener() {
            @Override
            public void onSliderChange(Slider slider, int index, int prevIndex) {
                String moneyString = "";
                mPriceText.setTextSize(20);
                for (int i = 0; i <= index; i += 2) {
                    moneyString += '$';
                }

                for (int i = 1; i <= index; i += 2) {
                    mPriceText.setTextSize(20 + (10 * i));
                }

                mPriceText.setText(moneyString);
                updateButtonText();
            }
        });
        mPriceText = (TextView) view.findViewById(R.id.find_food_price_textview);
        mPriceText.setText("$");

        mDistanceSlider = (Slider) view.findViewById(R.id.find_food_distance_slider);
        mDistanceSlider.setOnSliderChangeListener(new Slider.OnSliderChangeListener() {
            @Override
            public void onSliderChange(Slider slider, int index, int prevIndex) {
                String distanceString = "";
                mDistanceText.setTextSize(20);
                for (int i = 0; i <= index; i ++) {
                    distanceString += '>';
                }

                mDistanceText.setText(distanceString);
                updateButtonText();
            }
        });
        mDistanceText = (TextView) view.findViewById(R.id.find_food_distance_textview);
        mDistanceText.setText(">");

        mPlaceDetail = (CardView) view.findViewById(R.id.find_food_place_detail);
        invisible = mPlaceDetail.getVisibility() == View.INVISIBLE;

        mButton = (Button) view.findViewById(R.id.find_food_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(invisible)
                    mPlaceDetail.setVisibility(View.VISIBLE);
                else
                    mPlaceDetail.setVisibility(View.INVISIBLE);

                invisible = mPlaceDetail.getVisibility() == View.INVISIBLE;
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    private void updateButtonText() {
        int priceIndex = mPriceSlider.getIndex();
        int distanceIndex = mDistanceSlider.getIndex();
        String buttonString = String.format("Find me %s food %s!",
                Price.values()[priceIndex].toString(),
                Distance.values()[distanceIndex].toString());
        mButton.setText(buttonString);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateButtonText();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.find_food_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
