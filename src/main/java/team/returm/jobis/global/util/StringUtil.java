package team.returm.jobis.global.util;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.recruitment.domain.enums.ProgressType;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static String joinStringList(List<String> request) {
        return request == null ? null : String.join(",", request);
    }

    public static String getHiringProgress(List<ProgressType> request) {
        return request.stream().map(Enum::toString)
                .collect(Collectors.joining(","));
    }

    public static List<String> divideString(String content) {
        return content == null ? null : List.of(content.split(","));
    }

    public static String generateRandomCode(int size) {
        return RandomString.make(size);
    }

    public static String nullToEmpty(String request) {
        if (request == null) {
            return "";
        }
        return request;
    }
}
