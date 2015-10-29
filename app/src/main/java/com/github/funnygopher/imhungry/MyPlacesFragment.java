package com.github.funnygopher.imhungry;

import android.app.Activity;
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
import android.widget.Toast;

public class MyPlacesFragment extends Fragment {

    public static final int REQUEST_NEW_PLACE = 0;

    private ListView mListView;
    private MyPlacesListAdapter mAdapter;

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

        mAdapter = new MyPlacesListAdapter(getActivity());

        mListView = (ListView) view.findViewById(R.id.my_places_listview);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = mAdapter.getItem(position);

                final Dialog dialog = new Dialog(getActivity());
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
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        FloatingActionButton mFab = (FloatingActionButton) view.findViewById(R.id.my_places_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TEMPNewPlaceActivity.class);
                startActivityForResult(intent, REQUEST_NEW_PLACE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Cancelled new place", Toast.LENGTH_SHORT).show();
        }

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_NEW_PLACE) {
                mAdapter.update();
                Toast.makeText(getActivity(), "New place created!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
