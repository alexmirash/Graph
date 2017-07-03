package com.alex.mirash.graph;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class PeriodUtils {
    public static final long SECOND_MILLIS = 1000;
    public static final long MILLIS_IN_MINUTE = SECOND_MILLIS * 60;
    public static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;
    public static final long MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    public static final long MILLIS_IN_WEEK = MILLIS_IN_DAY * 7;
    public static final long SECONDS_IN_MONTH = 31 * 7 * 24 * 60 * 60;
    public static final long SECONDS_IN_HOUR = 3600;

    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    // non-instantiable class
    private PeriodUtils() {
        throw new AssertionError("DateUtils - non-instantiable class");
    }

    public static long getCurrentMonthDurationInMillis() {
        return Calendar.getInstance().getActualMaximum(Calendar.DATE) * 24 * 60 * 60 * 1000;
    }

    /**
     * @return Milliseconds for the specified DateTime without time.
     */
    public static long getTodayStartPointTimeMillis() {
        Calendar todayStartPoint = Calendar.getInstance();
        todayStartPoint.set(Calendar.HOUR_OF_DAY, 0);
        todayStartPoint.set(Calendar.MINUTE, 0);
        todayStartPoint.set(Calendar.SECOND, 0);

        return todayStartPoint.getTimeInMillis();
    }

    public static boolean isDayLightTime(long time) {
        return TimeZone.getDefault().inDaylightTime(new Date(time));
    }

    public static DayLightSavingTimeEvent findDSTTimeChange(long leftEdgeTime, long rightEdgeTime, long range) {
        boolean isLeft = isDayLightTime(leftEdgeTime);
        boolean isRight = isDayLightTime(rightEdgeTime);
        if (isLeft == isRight) return null;
        return findDSTTime(leftEdgeTime, rightEdgeTime, range, isLeft, isRight);
    }

    private static DayLightSavingTimeEvent findDSTTime(long leftEdgeTime, long rightEdgeTime, long range, boolean leftValue, boolean rightValue) {
        if (rightEdgeTime - leftEdgeTime < range) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(rightValue ? rightEdgeTime : leftEdgeTime);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return new DayLightSavingTimeEvent(calendar.getTimeInMillis(), rightValue);
        }
        long halfTime = (leftEdgeTime + rightEdgeTime) / 2;
        boolean halfValue = isDayLightTime(halfTime);
        if (leftValue != halfValue) {
            return findDSTTime(leftEdgeTime, halfTime, range, leftValue, halfValue);
        } else {
            return findDSTTime(halfTime, rightEdgeTime, range, halfValue, rightValue);
        }
    }

    public static class DayLightSavingTimeEvent {
        long time;
        boolean isToSummerTime;

        public DayLightSavingTimeEvent(long time, boolean isToSummerTime) {
            this.time = time;
            this.isToSummerTime = isToSummerTime;
        }

        public long getTime() {
            return time;
        }

        public boolean isToSummerTime() {
            return isToSummerTime;
        }
    }

    public static Calendar getCurrentWeekMondayTime() {
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.clear(Calendar.MINUTE);
        now.clear(Calendar.SECOND);
        now.clear(Calendar.MILLISECOND);
        now.set(Calendar.DAY_OF_WEEK, now.getFirstDayOfWeek());
        now.set(Calendar.HOUR_OF_DAY, 10);
        return now;
    }

    public static Calendar getCurrentWeekMondayTimeFromZeroHour() {
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.clear(Calendar.MINUTE);
        now.clear(Calendar.SECOND);
        now.clear(Calendar.MILLISECOND);
        now.set(Calendar.DAY_OF_WEEK, now.getFirstDayOfWeek());
        return now;
    }

    public static Date parseDate(String dateString, Locale locale) {
        try {
            return new SimpleDateFormat(PeriodUtils.DATE_PATTERN, locale).parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static long getStartOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
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

    public static String getFormattedCurrentTime() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN, Locale.US);
        return dateFormatter.format(new Date());
    }

    public static boolean isSameDay(Calendar date1, Calendar date2) {
        return (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) && date1.get(Calendar.DAY_OF_YEAR) == date2
                .get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isSameWeek(Calendar date1, Calendar date2) {
        return (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) && date1.get(Calendar.WEEK_OF_YEAR) == date2
                .get(Calendar.WEEK_OF_YEAR));
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
