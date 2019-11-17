package info.udaysingh.chalo;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import info.udaysingh.chalo.RecyclerClick.RecyclerViewClickListener;
import info.udaysingh.chalo.RecyclerClick.RecyclerViewTouchListener;
import info.udaysingh.chalo.adapter.RouteViewAdapter;
import info.udaysingh.chalo.api.APIinterface;
import info.udaysingh.chalo.api.RouteList;
import info.udaysingh.chalo.api.StopDataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    int currentMarker=0;
    private GoogleMap mMap;
    //ArrayList markerList = new ArrayList();
    List<Marker> markerList = new ArrayList<Marker>();

    Intent intent;
    APIinterface apIinterface;

    List<RouteList> routeViewList = new ArrayList<RouteList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
            intent =getIntent();
        //Log.e("data", String.valueOf(intent.getIntExtra("stopDataPosition",0)));

        // Add a marker in Sydney and move the camera
        List<LatLng> latLngList = new ArrayList<LatLng>();
        int size = intent.getIntExtra("data_size",0);
        for (int i =0;i<size;i++) {
            latLngList.add(new LatLng(intent.getDoubleExtra("lat_"+i, 0), intent.getDoubleExtra("lng_"+i, 0)));
            if(i==0)
            {
                    markerList.add(mMap.addMarker(new MarkerOptions().position(new LatLng(intent
                        .getDoubleExtra("lat_"+i, 0), intent.getDoubleExtra("lng_"+i, 0)))
                        .title(intent.getStringExtra("name_"+i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.person_pin))
                ));


            }
            else
            {
               markerList.add(mMap.addMarker(new MarkerOptions()
                       .position(new LatLng(intent.getDoubleExtra("lat_"+i, 0), intent.getDoubleExtra("lng_"+i, 0)))
                       .title(intent.getStringExtra("name_"+i)).icon( BitmapDescriptorFactory.fromResource(R.drawable.bus2x))));

                mMap.addCircle(new CircleOptions()
                        .center(new LatLng(intent.getDoubleExtra("lat_"+i, 0), intent.getDoubleExtra("lng_"+i, 0)))
                        .radius(60)
                        .strokeWidth(3f).strokeColor(Color.RED).fillColor(R.color.colorAccent));
            }
        }
    //    mMap.addMarker(new MarkerOptions().position(value1).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(intent.getDoubleExtra("lat_"+0, 0), intent.getDoubleExtra("lng_"+0, 0)), 15));
        mMap.addPolyline(new PolylineOptions().addAll(latLngList).width(4f).color(Color.RED));
        final Handler h = new Handler();
        final int delay = 10 * 1000;


        h.postDelayed(new Runnable(){
            public void run(){
                markerList.get(currentMarker).remove();
                currentMarker++;



                try {
                    markerList.get(currentMarker).remove();
                }catch (Exception e)
                {
                    startActivity(new Intent(MapsActivity.this,MainActivity.class));
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(intent.getDoubleExtra("lat_"+currentMarker, currentMarker), intent.getDoubleExtra("lng_"+0, 0)), 15));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(intent.getDoubleExtra("lat_"+currentMarker, 0), intent.getDoubleExtra("lng_"+currentMarker, 0)))
                        .title(intent.getStringExtra("name_"+currentMarker)).icon( BitmapDescriptorFactory.fromResource(R.drawable.person_pin)));




                //                markerList.get(currentMarker).setPosition(new LatLng(intent
//                        .getDoubleExtra("lat_"+currentMarker, 0), intent.getDoubleExtra("lng_"+currentMarker, 0)));

                h.postDelayed(this, delay);


            }
        }, delay);
    }

}
