package info.udaysingh.chalo.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("/metadata")
    Call<List<RouteList>> getAllRoutes();
}
