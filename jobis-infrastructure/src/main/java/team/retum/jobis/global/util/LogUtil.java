package team.retum.jobis.global.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {

    public static String stackTraceToString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }

    private LogUtil() {
    }
}
