package com.github.funnygopher.imhungry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AddPlaceFragment extends Fragment {

    private Slider mPriceSlider;
    private TextView mPriceText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_place, container, false);

        mPriceSlider = (Slider) view.findViewById(R.id.slider_add_place_price);
        mPriceText = (TextView) view.findViewById(R.id.tv_add_place_price);
        mPriceText.setText("$");

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
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
