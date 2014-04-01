package com.watch.project;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {

    private final LatLng mPosition;

    public MyItem(LatLng latLng) {

        mPosition = latLng;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}