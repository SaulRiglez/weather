package com.yoprogramo.earthquake.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeatureService {
    @GET("/earthquakes/feed/v1.0/summary/2.5_day.geojson")
    Call<FeatureCollection> getEarthquakes();
}
