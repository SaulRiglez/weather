package com.yoprogramo.earthquake.earth.mvvm;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import com.yoprogramo.earthquake.earth.model.Earthquake;
import com.yoprogramo.earthquake.earth.util.RetrofitHelper;
import com.yoprogramo.earthquake.earth.model.Feature;
import com.yoprogramo.earthquake.earth.model.FeatureCollection;
import com.yoprogramo.earthquake.earth.model.Properties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Earthquake>> earthquakes;
    private FetchDataAsyncTask asyncTask;

    public EarthquakeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        if (earthquakes == null) {
            earthquakes = new MutableLiveData<>();
            loadEarthquakes();
        }
        return earthquakes;
    }

    private void loadEarthquakes() {
        Call<FeatureCollection> featureCollectionCall = RetrofitHelper.Factory.createCallable();

        featureCollectionCall.enqueue(new Callback<FeatureCollection>() {
            @Override
            public void onResponse(Call<FeatureCollection> call, Response<FeatureCollection> response) {
                if (response.isSuccessful() && response.body() != null) {
                    earthquakes.setValue(getEarthquakesList(response.body().getFeatures()));
                }
            }

            @Override
            public void onFailure(Call<FeatureCollection> call, Throwable t) {
                Log.d("Photo", "onFailure: " + t.getMessage());
            }
        });

//        AsyncTask implementation;
//        asyncTask = new FetchDataAsyncTask(earthquakes);
//        asyncTask.execute();
    }

    private List<Earthquake> getEarthquakesList(List<Feature> features) {
        List<Earthquake> earthquakesList = new ArrayList<>(0);
        for (Feature feature : features) {
            Properties properties = feature.getProperties();
            Earthquake earthquake = new Earthquake(properties.getIds(),
                    new Date(),
                    properties.getDetail(), new Location("dummy location"),
                    properties.getMag(), properties.getUrl());
            earthquakesList.add(earthquake);
        }

        return earthquakesList;
    }
}
