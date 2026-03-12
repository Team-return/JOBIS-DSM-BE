package team.retum.jobis.common.spi;

import java.util.List;

public interface CachePort {

    List<Long> getRecentCompanyId(Long studentId);
}
