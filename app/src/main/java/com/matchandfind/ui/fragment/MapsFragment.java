package com.matchandfind.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.matchandfind.MatchAndFindApp;
import com.matchandfind.R;
import com.matchandfind.database.IDBManager;
import com.matchandfind.model.Person;
import com.matchandfind.ui.fragment.listener.OnPersonsUpdatesListeners;
import com.matchandfind.ui.fragment.map.MapTarget;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class MapsFragment extends Fragment implements OnPersonsUpdatesListeners {

    @Inject
    IDBManager dbManager;

    public static MapsFragment getInstance() {
        return new MapsFragment();
    }

    private static final int ICON_SIZE = 100;
    private static final int ZOOM = 12;

    private GoogleMap mMap;
    private MapTarget mapTarget;
    private OnMapReadyCallback mCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
            setPersonsListOnMap();
        }
    };
    private Subscription mDBPersonsSubscription;
    private SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MatchAndFindApp.getInstance().getComponent().inject(this);
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
            mapFragment.getMapAsync(mCallback);
        }

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mMap != null) {
            setPersonsListOnMap();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mDBPersonsSubscription != null && !mDBPersonsSubscription.isUnsubscribed()) {
            mDBPersonsSubscription.unsubscribe();
            mDBPersonsSubscription = null;
        }
    }

    private void setPersonsListOnMap() {
        mMap.clear();
        List<Person> persons = dbManager.getPersons();

        if (persons == null) return;
        LatLng firstPersonToZoom = null;
        for (Person person : persons) {
            LatLng location = new LatLng(person.getLat(), person.getLon());
            MarkerOptions options = new MarkerOptions().position(location).draggable(true);
            mapTarget = new MapTarget(mMap.addMarker(options));
            Picasso.with(getActivity())
                    .load(person.getPhoto())
                    .placeholder(R.drawable.offline_user)
                    .resize(ICON_SIZE, ICON_SIZE)
                    .into(mapTarget);

            if (persons.indexOf(person) == 0) {
                firstPersonToZoom = location;
            }
        }
        //zoom to first location
        if (firstPersonToZoom != null) {
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(ZOOM);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(firstPersonToZoom));
            mMap.animateCamera(zoom);
        }
    }

    @Override
    public void onPersonUpdate(Person person) {
//        setPersonsListOnMap();
        if (mapFragment != null) {
            mapFragment.getMapAsync(mCallback);
        }
    }

    @Override
    public void onListUpdate() {
//        setPersonsListOnMap();
        if (mapFragment != null) {
            mapFragment.getMapAsync(mCallback);
        }
    }
}
