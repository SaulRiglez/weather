package com.yoprogramo.earthquake.earth.util;

import com.yoprogramo.earthquake.earth.model.FeatureCollection;
import com.yoprogramo.earthquake.earth.model.FeatureService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String QUAKE_FEED = "https://earthquake.usgs.gov";

    public static class Factory {
       static Retrofit retrofit = create();
        static FeatureService service = retrofit.create(FeatureService.class);

        private static Retrofit create() {
            return new Retrofit.Builder()
                    .baseUrl(QUAKE_FEED)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

       public static Call<FeatureCollection> createCallable() {
            return service.getEarthquakes();
        }
    }
}

