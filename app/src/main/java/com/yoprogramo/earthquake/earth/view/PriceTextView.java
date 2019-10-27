package com.yoprogramo.earthquake.earth.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.yoprogramo.earthquake.R;

import java.text.NumberFormat;

import androidx.appcompat.widget.AppCompatTextView;

public class PriceTextView extends AppCompatTextView {
    private static NumberFormat CURRENCY_FORMAT = NumberFormat.getNumberInstance();
    private float price;

    public PriceTextView(Context context) {
        super(context);
    }

    public PriceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PriceTextView, defStyleAttr, 0);

        if (a.hasValue(R.styleable.PriceTextView_price)) {
            setPrice(a.getFloat(R.styleable.PriceTextView_price, 0));
        }
        a.recycle();
    }

    public void setPrice(float price) {
        this.price = price;
        setText(CURRENCY_FORMAT.format(price));
    }

    public float getPrice() {
        return price;
    }
}
