package com.matchandfind.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.matchandfind.MatchAndFindApp;
import com.matchandfind.R;
import com.matchandfind.model.Person;
import com.matchandfind.ui.activity.FragmentActionsListener;
import com.matchandfind.ui.fragment.listeners.OnUpdatePersonsListListener;

import java.util.List;

public class MapsFragment extends Fragment implements OnUpdatePersonsListListener {

    public static MapsFragment getInstance() {
        return new MapsFragment();
    }

    private GoogleMap mMap;
    private OnMapReadyCallback mCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
            mapFragment.getMapAsync(mCallback);
        }

        return root;
    }

    @Override
    public void onPersonsListUpdated(List<Person> personList) {
        setPersonsListOnMap(personList);
    }

    private void setPersonsListOnMap(List<Person> persons) {
        if (persons == null) return;
        for (Person person : persons) {
            LatLng sydney = new LatLng(person.getLat(), person.getLon());
            mMap.addMarker(new MarkerOptions().position(sydney)
                    .title(person.getStatus())
                    .draggable(true));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}
