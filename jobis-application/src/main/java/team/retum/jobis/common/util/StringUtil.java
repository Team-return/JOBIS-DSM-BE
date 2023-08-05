package team.retum.jobis.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static <E> String joinStringList(List<E> request) {
        return request == null ? null : String.join(",", request.stream().map(Object::toString).toList());
    }

    public static String mergeString(String str1, String str2) {
        return str1 + " " + str2;
    }

    public static List<String> divideString(String content) {
        if (content == null || content.isEmpty()) {
            return List.of();
        }
        return List.of(content.split(","));
    }

    public static String generateRandomCode(int size) {
        return RandomString.make(size);
    }
}
