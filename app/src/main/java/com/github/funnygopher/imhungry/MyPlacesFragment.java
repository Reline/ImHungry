package com.github.funnygopher.imhungry;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyPlacesFragment extends Fragment {

    private FloatingActionButton mFab;

    public static MyPlacesFragment newInstance() {
        MyPlacesFragment fragment = new MyPlacesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_places, container, false);

        final ListView mListView = (ListView) view.findViewById(R.id.my_places_listview);
        final MyPlacesListAdapter adapter = new MyPlacesListAdapter(new ArrayList<Place>());
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = adapter.getItem(position);

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.place_detail_cardview);

                TextView title = (TextView) dialog.findViewById(R.id.place_detail_card_title);
                TextView priceDistance = (TextView) dialog.findViewById(R.id.place_detail_card_price_distance);
                TextView description = (TextView) dialog.findViewById(R.id.place_detail_card_description);

                title.setText(place.getName());
                priceDistance.setText(place.getPrice().toString() + " - " + "2.0 mi");
                description.setText(place.getDescription());

                dialog.show();

                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                /*
                Long placeId = place._id;

                Bundle dataBundle = new Bundle();
                dataBundle.putLong("placeId", placeId);

                Intent intent = new Intent(getActivity().getApplicationContext(), PlaceDetailActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                */
            }
        });

        mFab = (FloatingActionButton) view.findViewById(R.id.my_places_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NewPlaceActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
