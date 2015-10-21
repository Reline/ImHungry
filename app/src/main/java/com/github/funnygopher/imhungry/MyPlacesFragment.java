package com.github.funnygopher.imhungry;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class MyPlacesFragment extends Fragment {

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

        ListView mListView = (ListView) view.findViewById(R.id.listview_my_places);
        MyPlacesListAdapter adapter = new MyPlacesListAdapter(new ArrayList<Place>());
        mListView.setAdapter(adapter);

        return view;
    }
}
