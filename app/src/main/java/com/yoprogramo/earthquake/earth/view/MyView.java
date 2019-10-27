package com.yoprogramo.earthquake.earth.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureHeight = measureHeight(heightMeasureSpec);
        int measureWidth = measureWidth(widthMeasureSpec);
//        MUST make this call to setMeasureDimension
//        or you will cause a runtime exception when
//        the control is laid out height
        setMeasuredDimension(measureHeight, measureWidth);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int measureWidth(int widthMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int result = 500;

        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {

            result = specSize;
        }

        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        int result = 500;

        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

//        find center
        int px = width / 2;
        int py = height / 2;

//        Create de new Paint brushes
//        For efficiency this should be done in
//        the views constructor.

        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);

        String displayText = "Hello view!!";

        float textWith = mTextPaint.measureText(displayText);

        canvas.drawText(displayText, px - textWith / 2, py, mTextPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int actionPerformanced = event.getAction();
        return true;
    }
}
