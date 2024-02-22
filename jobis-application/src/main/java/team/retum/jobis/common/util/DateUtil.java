package team.retum.jobis.common.util;

import java.time.LocalDate;

public class DateUtil {
    public static boolean between(LocalDate target, LocalDate start, LocalDate end) {
        if (start == null && end == null) return true;
        if (start == null) return target.isBefore(end);
        if (end == null) return target.isAfter(start);
        return target.isAfter(start) && target.isBefore(end);
    }
}
