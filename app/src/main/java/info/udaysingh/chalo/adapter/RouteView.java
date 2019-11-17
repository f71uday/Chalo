package info.udaysingh.chalo.adapter;

public class RouteView {
    String routeName;
    String startStopName;
    String endStopName;

    public RouteView() {
    }

    public RouteView(String routeName, String startStopName, String endStopName) {
        this.routeName = routeName;
        this.startStopName = startStopName;
        this.endStopName = endStopName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartStopName() {
        return startStopName;
    }

    public void setStartStopName(String startStopName) {
        this.startStopName = startStopName;
    }

    public String getEndStopName() {
        return endStopName;
    }

    public void setEndStopName(String endStopName) {
        this.endStopName = endStopName;
    }
}
