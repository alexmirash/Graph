package com.alex.mirash.graph.graph.tool;

import com.alex.mirash.graph.GraphApp;
import com.alex.mirash.graph.PeriodUtils;
import com.alex.mirash.graph.R;

/**
 * @author Mirash
 */

public class GraphUtils {
    private static final float PIXELS_IN_WEEK = GraphApp.getInstance().getResources().getDimension(R.dimen.graph_pixels_in_week_period_week);
    private static final float TIME_IN_PIXEL_WEEK = PeriodUtils.MILLIS_IN_WEEK / PIXELS_IN_WEEK;
    private static final float WEEK_PIXELS_IN_TIME_MILLI = PIXELS_IN_WEEK / PeriodUtils.MILLIS_IN_WEEK;

    public static float timeIntervalToPixels(long timeInterval) {
        return timeInterval / TIME_IN_PIXEL_WEEK;
    }

    public static long pixelsToTimeInterval(float pixels) {
        return (long) (pixels * TIME_IN_PIXEL_WEEK);
    }
}
