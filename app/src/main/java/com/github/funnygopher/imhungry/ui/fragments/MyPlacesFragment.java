package com.github.funnygopher.imhungry.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.database.DatabaseAccessObject;
import com.github.funnygopher.imhungry.model.database.RealmAccessObject;
import com.github.funnygopher.imhungry.ui.activities.NewPlaceActivity;
import com.github.funnygopher.imhungry.ui.recyclerview.adapters.PlaceRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.OrderedRealmCollection;

public class MyPlacesFragment extends Fragment {

    public static final int REQUEST_NEW_PLACE = 0;
    private static final String FILTER_KEY = "name";

    DatabaseAccessObject dao;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new RealmAccessObject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_places, container, false);
        ButterKnife.bind(this, view);

        PlaceRecyclerAdapter adapter = new PlaceRecyclerAdapter(getContext(), (OrderedRealmCollection<Place>) dao.getAllPlaces(), FILTER_KEY);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.my_places_fab)
    void onFabClick() {
        Intent intent = new Intent(getActivity().getApplicationContext(), NewPlaceActivity.class);
        startActivityForResult(intent, REQUEST_NEW_PLACE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Cancelled new place", Toast.LENGTH_SHORT).show();
        }

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_NEW_PLACE) {
                Toast.makeText(getActivity(), "New place created!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.close();
    }
}
