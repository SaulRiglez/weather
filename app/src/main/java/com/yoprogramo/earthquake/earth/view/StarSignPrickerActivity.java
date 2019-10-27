package com.yoprogramo.earthquake.earth.view;

import android.content.Intent;
import android.os.Bundle;

import com.yoprogramo.earthquake.R;
import com.yoprogramo.earthquake.StarSignAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class StarSignPrickerActivity extends AppCompatActivity {

    public static final String EXTRA_SIGN_NAME = "SIGN_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_sign_pricker);

        StarSignAdapter adapter = new StarSignAdapter();
        adapter.setOnAdapterItemClick(new IAdapterItemClick() {
            @Override
            public void onItemClicked(String selectedItem) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_SIGN_NAME, selectedItem);
                setResult(1, intent);
                finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
    }
}
