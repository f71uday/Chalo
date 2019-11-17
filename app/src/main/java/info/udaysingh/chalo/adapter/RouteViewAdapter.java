package info.udaysingh.chalo.adapter;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.udaysingh.chalo.R;
import info.udaysingh.chalo.api.RouteList;
import info.udaysingh.chalo.api.StopDataList;
import okhttp3.Route;

public class RouteViewAdapter extends RecyclerView.Adapter<RouteViewAdapter.MyViewHolder>{
    private List<RouteList> routeViewList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_view_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RouteList routeView = routeViewList.get(position);
        holder.routeName.setText(routeView.getRouteName());
//        holder.startStop.setText(routeView.getStopDataList().get(1).getStopName());
//        holder.endStop.setText(routeView.getStopDataList().get(routeView.getStopDataList().size()-1).getStopName());
//       // ArrayAdapter<StopDataList> dataAdapter = new ArrayAdapter<StopDataList>(, android.R.layout.simple_spinner_item, routeViewList.get(position).getStopDataList());

    }

    @Override
    public int getItemCount() {
        return routeViewList.size();
    }

    public RouteViewAdapter(List<RouteList> routeViewList) {
        this.routeViewList = routeViewList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView routeName,startStop,endStop;
        public Spinner startSpinner,stopSpinner;

        public MyViewHolder(View view) {
            super(view);
            routeName = (TextView) view.findViewById(R.id.route_name);
            startStop = (TextView) view.findViewById(R.id.start_stop);
            endStop = (TextView) view.findViewById(R.id.end_stop);
//            startSpinner = (Spinner) view.findViewById(R.id.start_spinner);
//            stopSpinner = (Spinner)view.findViewById(R.id.start_spinner);

        }
    }


}
