package ru.mirea.komanda.map;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private final int MY_PERMISSIONS_REQUEST_LOCATE = 100;
    private boolean isWork = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        int permissionStatus = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) ;
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATE) ;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        LatLng mirea = new LatLng(55.670005, 37.479894);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(mirea).zoom(12).build();
        mMap.addMarker(new MarkerOptions().position(mirea).title("МИРЭА").snippet("Крупнейший политехнический ВУЗ"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mirea));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setZoomControlsEnabled(true) ;
        mMap.getUiSettings().setCompassEnabled(true) ;
        mMap.setTrafficEnabled(true);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                latLng).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().title("Где я?")
                .snippet("Новое место").position(latLng));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isWork = true;
                } else {
                    isWork = false;
                }
                return;
            }
        }
    }
}
