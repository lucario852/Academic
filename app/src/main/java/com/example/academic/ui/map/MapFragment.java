package com.example.academic.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.academic.R;
import com.example.academic.databinding.FragmentMapBinding;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapViewModel slideshowViewModel;
    private FragmentMapBinding binding;
    private GoogleMap mGoogleMap;
    private MapView mapView;
    private MapsInitializer.Renderer mapsInitializer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_map,container,false);
        mapView=(MapView) vista.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);





        return  vista;


    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

          LatLng universidad = new LatLng(4.7955753,-75.6901787);
         mGoogleMap.addMarker(new MarkerOptions().position(universidad).title("Institucion educativa"));
         mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(universidad, 16));

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mapView.onDestroy();
    }
}