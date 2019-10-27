package com.yoprogramo.earthquake.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yoprogramo.earthquake.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class CompassView extends View {

    private Paint markerPaint;
    private Paint textPaint;
    private Paint circlePaint;
    private String northString;
    private String southString;
    private String westString;
    private String eastString;
    private int textHeight;

    private float mBearing;


    public CompassView(Context context) {
        super(context);

        Log.d("Photo", "CompassView: " + "context");

    }

    public CompassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        Log.d("Photo", "CompassView: " + "context attrs");
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.d("Photo", "CompassView: " + "attrs attrs defStyleAttr");
        setFocusable(true);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CompassView, defStyleAttr, 0);
        if (a.hasValue(R.styleable.CompassView_bearing)) {
            setmBearing(a.getFloat(R.styleable.CompassView_bearing, 0));
        }
        a.recycle();

        drawCompass(this.getContext());
    }

    private void drawCompass(Context context) {
        Resources resources = context.getResources(); // Test the way I could think.


        northString = resources.getString(R.string.cardinal_north);
        southString = resources.getString(R.string.cardinal_south);
        eastString = resources.getString(R.string.cardinal_east);
        westString = resources.getString(R.string.cardinal_weast);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(ContextCompat.getColor(context, R.color.text_color));

        textHeight = (int) textPaint.measureText("yY");



        markerPaint.setColor(ContextCompat.getColor(context, R.color.marker_color));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWith = measure(widthMeasureSpec);
        int measureHeigth = measure(heightMeasureSpec);
        int d = Math.min(measureWith, measureHeigth);
        setMeasuredDimension(d, d);
    }

    private int measure(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 200;
        } else {
            result = specSize;
        }
        return result;
    }

    public float getmBearing() {
        return mBearing;
    }

    public void setmBearing(float mBearing) {
        this.mBearing = mBearing;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mMeasurewith = getMeasuredWidth();
        int mMeasureHight = getMeasuredHeight();

        int px = mMeasurewith / 2;
        int py = mMeasureHight / 2;
        int radius = Math.min(px, py);



        if(circlePaint != null){
            canvas.drawCircle(px, py, radius, circlePaint);
            canvas.save();
        }
        Log.d("Photo", "onDraw: " + "onDraw");

    }
}
