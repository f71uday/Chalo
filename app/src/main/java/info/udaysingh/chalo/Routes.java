package info.udaysingh.chalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;


import com.arlib.floatingsearchview.FloatingSearchView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import info.udaysingh.chalo.RecyclerClick.RecyclerViewClickListener;
import info.udaysingh.chalo.RecyclerClick.RecyclerViewTouchListener;
import info.udaysingh.chalo.adapter.RouteView;
import info.udaysingh.chalo.adapter.RouteViewAdapter;
import info.udaysingh.chalo.api.APIClient;
import info.udaysingh.chalo.api.APIinterface;
import info.udaysingh.chalo.api.RouteList;
import info.udaysingh.chalo.api.StopDataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Routes extends AppCompatActivity {

    APIinterface apIinterface;
    List<RouteList> routeViewList = new ArrayList<RouteList>();
    RecyclerView recyclerView;
    String data;
    RouteViewAdapter routeViewAdapter;
    FloatingSearchView floatingSearchView;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_routes);
        apIinterface = APIClient.getClient().create(APIinterface.class);
        final ProgressDialog progressDialog = ProgressDialog.show(this,"Loading","Please wait...",true);
        floatingSearchView = (FloatingSearchView)findViewById(R.id.floating_search_view);
        int timestart = (int) System.currentTimeMillis();
        // sleep the thread, whatever time you want.
                    Call<List<RouteList>> call = apIinterface.getAllRoutes();
                    call.enqueue(new Callback<List<RouteList>>() {
                        @Override
                        public void onResponse(Call<List<RouteList>> call, Response<List<RouteList>> response) {
                            progressDialog.dismiss();

                            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            routeViewList= response.body();
                            routeViewAdapter = new RouteViewAdapter(response.body());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(routeViewAdapter);

                            recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    // Toast.makeText(getApplicationContext(), bookList.get(position).getTitle() + " is clicked!", Toast.LENGTH_SHORT).show();
                                    Intent mapIntent = new Intent(Routes.this,MapsActivity.class);
                                    mapIntent.putExtra("data_size",routeViewList.get(position).getStopDataList().size());
                                    for(int i =0;i<routeViewList.get(position).getStopDataList().size();i++) {
                                        mapIntent.putExtra("lng_"+i,routeViewList.get(position).getStopDataList().get(i).getLongitude());
                                        mapIntent.putExtra("lat_"+i,routeViewList.get(position).getStopDataList().get(i).getLatitute());
                                        mapIntent.putExtra("name_"+i,routeViewList.get(position).getStopDataList().get(i).getStopName());
                                    }
                                    Toast.makeText(getApplicationContext(),"Please wait for 10 sec for Camara to zoom",Toast.LENGTH_LONG).show();
                                    startActivity(mapIntent);
                                }

                                @Override
                                public void onLongClick(View view, int position) {
                                    //  Toast.makeText(getApplicationContext(), bookList.get(position).getTitle() + " is long pressed!", Toast.LENGTH_SHORT).show();

                                }
                            }));
                        }

                        @Override
                        public void onFailure(Call<List<RouteList>> call, Throwable t) {
                                Log.e("API call",t.getMessage());
                        }
                    });
                int timeend = (int) System.currentTimeMillis();
                Log.e("execution time",(timeend-timestart)+"");



        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
                //floatingSearchView.swapSuggestions();
            }
        });


    }
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<RouteList> filterdList = new ArrayList<>();
    /*
        //looping through existing elements
        for (String s : names) {
            //if the existing elements contains the search input
            if (s.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
            }
        }

        //calling a method of the adapter class and passing the filtered list
        //adapter.filterList(filterdNames);*/
    }

}
