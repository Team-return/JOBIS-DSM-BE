package team.retum.jobis.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static <E> String joinStringList(List<E> request, String key) {
        return request == null ? null : String.join(key, request.stream().map(Object::toString).toList());
    }

    public static List<String> divideString(String content, String key) {
        if (content == null || content.isEmpty() || content.isBlank()) {
            return List.of();
        }

        List<String> dividedList = Arrays.asList(content.split(key));

        return new ArrayList<>(dividedList);
    }
}
