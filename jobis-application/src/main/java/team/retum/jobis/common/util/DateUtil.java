package team.retum.jobis.common.util;

import java.time.LocalDate;

public class DateUtil {
    public static boolean between(LocalDate target, LocalDate start, LocalDate end) {
        return target.isAfter(start) && target.isBefore(end);
    }
}
