package team.retum.jobis.common.spi;

import java.util.Set;

public interface CachePort {

    Set<Long> getRecentCompanyId(Long studentId);
}
