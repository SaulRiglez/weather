package com.yoprogramo.earthquake.earth.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yoprogramo.earthquake.R;

import androidx.annotation.Nullable;

public class MyCompoundView extends LinearLayout {

    EditText editText;
    Button clearButton;


    public MyCompoundView(Context context) {
        super(context);
    }

    public MyCompoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCompoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //Inflate the view from the layout resource.
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.clearable_edit_text, this, true);

        //Get reference to the child controls
        editText = findViewById(R.id.editText);
        clearButton = findViewById(R.id.button);

        hookupButton();
    }

    private void hookupButton() {
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
    }
}
