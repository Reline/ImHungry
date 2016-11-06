package com.github.funnygopher.imhungry.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.presenters.MyPlacesPresenter;
import com.github.funnygopher.imhungry.ui.activities.NewPlaceActivity;
import com.github.funnygopher.imhungry.ui.recyclerview.adapters.PlaceRecyclerAdapter;
import com.github.funnygopher.imhungry.ui.views.MyPlacesView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPlacesFragment extends Fragment implements MyPlacesView {

    public static final int REQUEST_NEW_PLACE = 0;
    private static final String FILTER_KEY = "name";

    MyPlacesPresenter presenter;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    PlaceRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyPlacesPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_places, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PlaceRecyclerAdapter(getContext(), null, FILTER_KEY);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
        adapter.updateData(presenter.getAllPlaces());
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @OnClick(R.id.my_places_fab)
    void onFabClick() {
        Intent intent = new Intent(getActivity().getApplicationContext(), NewPlaceActivity.class);
        startActivityForResult(intent, REQUEST_NEW_PLACE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            Snackbar.make(coordinatorLayout,
                    "Cancelled creating new place",
                    Snackbar.LENGTH_SHORT)
                    .setAction("dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // no-op
                        }
                    })
                    .show();
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_NEW_PLACE) {
            Snackbar.make(coordinatorLayout,
                    "New place created",
                    Snackbar.LENGTH_SHORT)
                    .setAction("dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // no-op
                        }
                    })
                    .show();
        }
    }
}
