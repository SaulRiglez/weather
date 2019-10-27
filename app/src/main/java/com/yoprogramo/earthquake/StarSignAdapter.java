package com.yoprogramo.earthquake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoprogramo.earthquake.earth.view.IAdapterItemClick;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StarSignAdapter extends RecyclerView.Adapter<StarSignAdapter.ViewHolder> {

    IAdapterItemClick itemClick;


    private String[] mStartSigns = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra",
            "Scorpio", "Sagittarious", "Capricorn", "Aquarious", "Pisces", " esto es deporte yoprogramo@gmail.com esto es un test"};

    public StarSignAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view, null);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(mStartSigns[position]);
        holder.listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClick != null) {
                    itemClick.onItemClicked(mStartSigns[position]);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return mStartSigns == null ? 0 : mStartSigns.length;
    }

    public void setOnAdapterItemClick(IAdapterItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public View.OnClickListener listener;

        public ViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.itemTextView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view);
            }
        }
    }
}
