package team.retum.jobis.global.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexProperty {

    public static final String STUDENT_EMAIL = "^.+@dsm.hs.kr$";

    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";

    public static final String WORKING_HOURS = "^([01][0-9]|2[0-3]):([0-5][0-9]) ~ ([01][0-9]|2[0-3]):([0-5][0-9])$";

    public static final String FILE_NAME = "^[a-zA-Z0-9_-]+\\.[a-zA-Z0-9]+$";

    public static final String EMAIL = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
}
