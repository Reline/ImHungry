package com.github.funnygopher.imhungry;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

import com.github.funnygopher.imhungry.views.Slider;

public class NewPlaceActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mSaveButton;

    private AppCompatEditText mNameEditText;
    private AppCompatEditText mDescEditText;
    private Slider mPriceSlider;
    private TextView mPriceText;

    private boolean hasTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);

        mToolbar = (Toolbar) findViewById(R.id.new_place_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        getSupportActionBar().setTitle("New Place");

        mNameEditText = (AppCompatEditText) findViewById(R.id.new_place_name);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSaveButton(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                Intent intent = new Intent();

                if (hasTitle) {
                    Place place = createPlace();

                    Bundle b = new Bundle();
                    b.putParcelable("place", place);
                    intent.putExtras(b);

                    setResult(Activity.RESULT_OK, intent);
                } else {
                    setResult(Activity.RESULT_CANCELED, intent);
                }

                finish();
            }
        });

        updateSaveButton(mNameEditText.getText().toString());
    }

    private void updateSaveButton(String s) {
        hasTitle = s.length() > 0;
        if(hasTitle) {
            mSaveButton.setText("Save");
        } else {
            mSaveButton.setText("Cancel");
        }
    }

    private Place createPlace() {
        String name = mNameEditText.getText().toString();
        String desc = mDescEditText.getText().toString();
        int price = Price.getValue(mPriceSlider.getIndex());
        Place place = new Place(name, desc, price, "", false);

        return place;

        /*
        // Adds the new place to the database
        CupboardDBHelper dbHelper = new CupboardDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cupboard().withDatabase(db).put(place);
        */
    }
}