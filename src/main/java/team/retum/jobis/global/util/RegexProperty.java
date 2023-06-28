package team.retum.jobis.global.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexProperty {

    public static final String EMAIL = "^.+@dsm.hs.kr$";

    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";

    public static final String COMPANY_PASSWORD = "^[0-9]{4}";
}
