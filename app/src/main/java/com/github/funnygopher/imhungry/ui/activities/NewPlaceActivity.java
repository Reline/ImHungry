package com.github.funnygopher.imhungry.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;
import com.github.funnygopher.imhungry.model.database.RealmService;
import com.github.funnygopher.imhungry.ui.widgets.Slider;

public class NewPlaceActivity extends AppCompatActivity {

    private RealmService realmService;

    private Toolbar mToolbar;
    private Button mSaveButton;

    private AppCompatEditText mNameEditText;
    private AppCompatEditText mDescEditText;
    private Slider mPriceSlider;
    private TextView mPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);

        realmService = new RealmService();

        mToolbar = (Toolbar) findViewById(R.id.new_place_toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.icons));
        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("New Place");

        mNameEditText = (AppCompatEditText) findViewById(R.id.new_place_name);

        mDescEditText = (AppCompatEditText) findViewById(R.id.new_place_description);
        mDescEditText.setHorizontallyScrolling(false);
        mDescEditText.setMaxLines(Integer.MAX_VALUE);

        // The price slider and textview
        mPriceSlider = (Slider) findViewById(R.id.new_place_price_slider);
        mPriceSlider.setOnSliderChangeListener(new Slider.OnSliderChangeListener() {
            @Override
            public void onSliderChange(Slider slider, int index, int prevIndex) {
                mPriceText.setText(Price.getName(index));
            }
        });
        mPriceText = (TextView) findViewById(R.id.new_place_price_textview);
        mPriceText.setText(Price.getName(mPriceSlider.getIndex()));

        mSaveButton = (Button) findViewById(R.id.new_place_button_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPlace();
            }
        });
    }

    private void createPlace() {
        String name = mNameEditText.getText().toString();
        String desc = mDescEditText.getText().toString();
        int price = Price.getValue(mPriceSlider.getIndex());
        Place place = new Place(name, desc, price, "", false);

        realmService.addPlace(place);

        Intent intent = new Intent();
        intent.putExtra("updateList", true);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}