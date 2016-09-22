package com.sked.continum;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sked.roadapiclient.Error;
import com.sked.roadapiclient.Location;
import com.sked.roadapiclient.Point;
import com.sked.roadapiclient.RoadApiClient;
import com.sked.roadapiclient.RoadSnapCallback;
import com.sked.roadapiclient.SnappedPoints;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private MenuItem showPolyline;
    private MenuItem showActualPoints;
    private MenuItem showSnappedPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        requestRoadApi();
    }

    private void requestRoadApi() {
        RoadApiClient
                .with(this)
                .interpolate(true)
                .spots(getDummySpots())
                .key("AIzaSyCCOYDUJcDUhfcROrziEJm9hAfKOM7tTgc")
                .roadSnapCallback(roadSnapCallback)
                .execute();
    }

    private void addMarker(LatLng latLng, float markerColor) {
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(getString(R.string.title_marker, latLng.latitude, latLng.longitude))
                .icon(BitmapDescriptorFactory.defaultMarker(markerColor)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private List<Location> getDummySpots() {
        List<Location> spots = new ArrayList<>();
        spots.add(new Location(28.546308, 77.209190));
        spots.add(new Location(28.543707, 77.208890));
        spots.add(new Location(28.542990, 77.217473));
        spots.add(new Location(28.542199, 77.227172));
        spots.add(new Location(28.540804, 77.234596));
        spots.add(new Location(28.537562, 77.232150));
        spots.add(new Location(28.537373, 77.232064));
        spots.add(new Location(28.537470, 77.232199));
        spots.add(new Location(28.530775, 77.231850));
        spots.add(new Location(28.532811, 77.229790));
        spots.add(new Location(28.531492, 77.224726));
        spots.add(new Location(28.531039, 77.221850));
        spots.add(new Location(28.530028, 77.218277));
        spots.add(new Location(28.529400, 77.215138));
        spots.add(new Location(28.528190, 77.215087));
        spots.add(new Location(28.526588, 77.215674));
        return spots;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    RoadSnapCallback roadSnapCallback = new RoadSnapCallback() {
        @Override
        public void onSnappedPointSuccess(SnappedPoints snappedPoints) {
            drawLines(snappedPoints.getPoints());
        }

        @Override
        public void onSnappedPointError(Error error) {

        }
    };

    private void drawLines(List<Point> points) {
        PolylineOptions options = new PolylineOptions();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Point point : points) {
            LatLng latLng = new LatLng(point.getLocation().getLatitude(), point.getLocation().getLongitude());
            options.add(latLng);
            builder.include(latLng);
            if (showActualPoints.isChecked()) if (point.getOriginalIndex() > -1)
                addMarker(latLng, BitmapDescriptorFactory.HUE_ROSE);
            if (showSnappedPoints.isChecked()) if (point.getOriginalIndex() == -1)
                addMarker(latLng, BitmapDescriptorFactory.HUE_GREEN);
        }
        if (showPolyline.isChecked())
            mMap.addPolyline(options.width(getResources().getDimensionPixelOffset(R.dimen.polyline_width)).color(ContextCompat.getColor(this, R.color.colorPrimary)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), getResources().getDimensionPixelOffset(R.dimen.map_padding)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        showPolyline = menu.findItem(R.id.actions_show_polylines);
        showActualPoints = menu.findItem(R.id.actions_show_actual_points);
        showSnappedPoints = menu.findItem(R.id.actions_show_snapped_points);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(!item.isChecked());
        mMap.clear();
        requestRoadApi();
        return super.onOptionsItemSelected(item);
    }
}
