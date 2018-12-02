package team383.edu.panthertheatres;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.ContentValues.TAG;


public class LocationsFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    // Initialize marker location constants
    private static final LatLng MAJESTIC = new LatLng(43.039898, -88.183390);
    private static final LatLng NORTH_SHORE = new LatLng(43.230883, -87.921600);
    private static final LatLng MENOMONEE_FALLS = new LatLng(43.185802, -88.134180);
    private static final LatLng SOUTH_SHORE = new LatLng(42.913042, -87.934108);
    private static final LatLng BISTRO_PLEX = new LatLng(42.950454, -88.002019);
    private static final LatLng RIDGE = new LatLng(42.949479, -88.106281);

    private static final LatLng WAUWATOSA = new LatLng(43.068170, -88.007826);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations, container, false);

        mMapView = (MapView) view.findViewById(R.id.locations_map);

        initGoogleMap(savedInstanceState);
        return view;
    }

    private void initGoogleMap(Bundle savedInstanceState){
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        // Add Markers
        map.addMarker(new MarkerOptions()
                .position(MAJESTIC)
                .title("Panther Theatres - Majestic Cinema")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.popcorn_pin)));

        map.addMarker(new MarkerOptions()
                .position(NORTH_SHORE)
                .title("Panther Theatres - North Shore")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.popcorn_pin)));

        map.addMarker(new MarkerOptions()
                .position(MENOMONEE_FALLS)
                .title("Panther Theatres - Menomonee Falls")
              .icon(BitmapDescriptorFactory.fromResource(R.drawable.popcorn_pin)));

        map.addMarker(new MarkerOptions()
                .position(SOUTH_SHORE)
                .title("Panther Theatres - South Shore")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.popcorn_pin)));

        map.addMarker(new MarkerOptions()
                .position(BISTRO_PLEX)
                .title("Panther Theatres - Bistro Plex")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.popcorn_pin)));

        map.addMarker(new MarkerOptions()
                .position(RIDGE)
                .title("Panther Theatres - Ridge")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.popcorn_pin)));


         // Move map camera to 43.096506, -88.018320 (Milwaukee)
         // Zoom levels:
         //             10 = City detail
         //             15 = Street-level detail
        CameraPosition cameraFrame = new CameraPosition.Builder()
                .target(WAUWATOSA)      // Sets the center of the map to Wauwatosa, WI
                .zoom(10)               // Sets the zoom
                .bearing(0)             // Sets the bearing to North
                .tilt(30)               // Sets the tilt to 30 Degrees
                .build();               // Creates a new CameraPosition from the builder

        // Updating the Camera View
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraFrame));



    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}