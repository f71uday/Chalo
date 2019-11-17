package info.udaysingh.chalo.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RouteList implements Serializable {
    @SerializedName("routeId")
    @Expose
    private String routeId;
    @SerializedName("routeName")
    @Expose
    private String routeName;
    @SerializedName("stopDataList")
    @Expose
    private List<StopDataList> stopDataList = null;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<StopDataList> getStopDataList() {
        return stopDataList;
    }

    public void setStopDataList(List<StopDataList> stopDataList) {
        this.stopDataList = stopDataList;
    }
}
