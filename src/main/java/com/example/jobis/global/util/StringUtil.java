package com.example.jobis.global.util;

import com.example.jobis.domain.recruitment.domain.enums.ProgressType;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static String getRequiredLicenses(List<String> request) {
        return request == null ? null : String.join(",", request);
    }

    public static String getHiringProgress(List<ProgressType> request) {
        return request.stream().map(Enum::toString)
                .collect(Collectors.joining(","));
    }

    public static List<String> divideString(String content) {
        return content==null ? null : List.of(content.split(","));
    }
}
