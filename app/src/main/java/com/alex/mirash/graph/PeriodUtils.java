package com.alex.mirash.graph;

import java.util.Calendar;

public final class PeriodUtils {
    public static final long SECOND_MILLIS = 1000;
    public static final long MILLIS_IN_MINUTE = SECOND_MILLIS * 60;
    public static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;
    public static final long MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    public static final long MILLIS_IN_WEEK = MILLIS_IN_DAY * 7;


    // non-instantiable class
    private PeriodUtils() {
        throw new AssertionError("DateUtils - non-instantiable class");
    }

    public static void applyEndOfWeek(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        applyStartOfDay(calendar);
        calendar.add(Calendar.MILLISECOND, -1);
    }


    public static void applyStartOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
    }

    public static void applyStartOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        applyStartOfDay(calendar);
    }
}
