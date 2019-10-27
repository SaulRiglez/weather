
package com.yoprogramo.earthquake;

import android.os.Bundle;

import com.yoprogramo.earthquake.databinding.ActivityEarthquakeMainBindingImpl;
import com.yoprogramo.earthquake.mvvm.EarthquakeViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class EarthquakeMainActivity extends AppCompatActivity {

    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    EarthquakeViewModel earthquakeViewModel;


    EarthquakeListFragment mEarthquakeListFragment;
    private ActivityEarthquakeMainBindingImpl binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_earthquake_main);

        FragmentManager fm = getSupportFragmentManager();

        // Android will automatically re-add any Fragments that
        // have previously been added after a configuration change,
        // so only add it if this isn't an automatic restart.
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();

            mEarthquakeListFragment = new EarthquakeListFragment();
            ft.add(R.id.main_activity_frame,
                    mEarthquakeListFragment, TAG_LIST_FRAGMENT);

            ft.commitNow();
        } else {
            mEarthquakeListFragment =
                    (EarthquakeListFragment) fm.findFragmentByTag(TAG_LIST_FRAGMENT);
        }

        earthquakeViewModel = ViewModelProviders.of(this).get(EarthquakeViewModel.class);

        earthquakeViewModel.getEarthquakes().observe(this, new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(List<Earthquake> earthquakes) {
                mEarthquakeListFragment.setEarthquakes(earthquakes);
            }
        });

//        Date now = Calendar.getInstance().getTime();
//        List<Earthquake> dummyQuakes = new ArrayList<Earthquake>(0);
//        dummyQuakes.add(new Earthquake("0", now, "San Jose", null, 7.3, null));
//        dummyQuakes.add(new Earthquake("1", now, "LA", null, 6.5, null));
//        mEarthquakeListFragment.setEarthquakes(dummyQuakes);
    }
}
