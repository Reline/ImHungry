package com.github.funnygopher.imhungry;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class SnappingSeekBar extends SeekBar {

    private int mSnappingPoints;
    private int[] mPoints;

    public SnappingSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setMax(100);
        // Retrieves attributes
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SnappingSeekBar, 0, 0);
        try {
            mSnappingPoints = array.getInt(R.styleable.SnappingSeekBar_snap_points, 2);
        } finally {
            if(array != null) {
                array.recycle();
            }
        }

        init();
    }

    private void init() {
        mPoints = new int[mSnappingPoints];

        // Sets the values for each points on the seek bar
        int pointValue = 100 / (mSnappingPoints - 1);
        for(int i = 0; i < mPoints.length; i++) {
            if(i == 0) {
                mPoints[i] = 0;
            } else {
                mPoints[i] = mPoints[i - 1] + pointValue;
            }
        }

        this.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();

                // Finds the closes point, and then snaps the bar to it
                int smallestABSIndex = 0;
                float smallestABS = -1;
                for(int i = 0; i < mPoints.length; i++) {
                    int abs = Math.abs(mPoints[i] - progress);
                    if(smallestABS == -1) {
                        smallestABS = abs;
                    }

                    if(abs < smallestABS) {
                        smallestABS = abs;
                        smallestABSIndex = i;
                    }
                }
                seekBar.setProgress(mPoints[smallestABSIndex]);
            }
        });
    }
}
