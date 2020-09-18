package br.com.senior.server.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DateUtils {

    public static boolean isWeekend(LocalDateTime date) {
        var d = date.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
    }
}
