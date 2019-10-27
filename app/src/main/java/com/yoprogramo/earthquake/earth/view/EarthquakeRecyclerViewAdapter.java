

package com.yoprogramo.earthquake.earth.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yoprogramo.earthquake.R;
import com.yoprogramo.earthquake.databinding.ListItemEarthquakeBinding;
import com.yoprogramo.earthquake.earth.model.Earthquake;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class EarthquakeRecyclerViewAdapter extends RecyclerView.Adapter<EarthquakeRecyclerViewAdapter.ViewHolder> {

    private List<Earthquake> mEarthquakes;

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.US);
    private static final NumberFormat MAGNITUDE_FORMAT = new DecimalFormat("0.0");

    public EarthquakeRecyclerViewAdapter(List<Earthquake> earthquakes) {
        mEarthquakes = earthquakes;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemEarthquakeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_earthquake, parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Earthquake earthquake = mEarthquakes.get(position);
        holder.binding.setEarthquake(earthquake);
    }

    @Override
    public int getItemCount() {
        return mEarthquakes.size();
    }

    void setData(final List<Earthquake> newEarthquakes) {
        final List<Earthquake> oldEarthquakes = mEarthquakes;

        mEarthquakes = newEarthquakes;

        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldEarthquakes != null ? oldEarthquakes.size() : 0;
            }

            @Override
            public int getNewListSize() {
                return newEarthquakes != null ? newEarthquakes.size() : 0;
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return oldEarthquakes.get(oldItemPosition).getId().equals(newEarthquakes.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return true;
            }
        }).dispatchUpdatesTo(this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ListItemEarthquakeBinding binding;

         ViewHolder(ListItemEarthquakeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setTimeformat(TIME_FORMAT);
            binding.setMagnitudeformat(MAGNITUDE_FORMAT);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + binding.details + "'";
        }
    }
}
