package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * GKislin
 * 07.01.2015.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(Object d, Object startDate, Object endTime) {
        if (d.getClass() == LocalTime.class)
        {
            return ((LocalTime)d).compareTo((LocalTime) startDate) >= 0 && ((LocalTime)d).compareTo((LocalTime) endTime) <= 0;
        }
        else
            return ((LocalDate)d).compareTo((LocalDate) startDate) >= 0 && ((LocalDate)d).compareTo((LocalDate) endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
