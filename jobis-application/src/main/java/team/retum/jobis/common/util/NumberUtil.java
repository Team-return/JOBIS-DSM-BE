package team.retum.jobis.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil {

    public static int getTotalPageCount(Long totalCount, int limit) {
        return (int) Math.ceil(
                totalCount.doubleValue() / limit
        );
    }
}
