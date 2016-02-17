package myapplication.helloworld;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import myapplication.bits.R;

public class GoogleMapsActivity extends FragmentActivity {

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.
    CameraUpdate cameraUpdate;
    private static final Drawable TRANSPARENT_DRAWABLE = new ColorDrawable(Color.TRANSPARENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        getLocation();
    }

    private void getLocation() {
        LatLng bangalore = new LatLng(12.9667, 77.5667);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(bangalore, 10);
        googleMap.moveCamera(cameraUpdate);

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);

        googleMap.addMarker(new MarkerOptions().position(bangalore)
                .title("Bangalore")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9500, 77.5900))
                .title("Lal Bagh")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.8339956, 77.4009816))
                .title("Wonderla")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(13.0094, 77.5508))
                .title("ISKCON Temple")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

// Make the background of marker transparent

        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.8850, 77.6210))
                .title("Devarachikkanahalli")
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.play))));
        googleMap.addCircle(new CircleOptions().center(bangalore).radius(500));

    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #googleMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (googleMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #googleMap} is not null.
     */
    private void setUpMap() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
