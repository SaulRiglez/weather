package com.yoprogramo.earthquake.mvvm;

import android.app.Application;

import com.yoprogramo.earthquake.Earthquake;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
        asyncTask = new FetchDataAsyncTask(earthquakes);
        asyncTask.execute();
    }
}
