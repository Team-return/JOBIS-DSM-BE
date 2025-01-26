package team.retum.jobis.common.spi;

import java.util.List;

public interface FeignClientPort {

    String getCompanyNameByBizNo(String businessNumber);

    boolean checkCompanyExistsByBizNo(String businessNumber);

    List<String> getMyInterestRecruitmentByMajorAndTech(List<String> major, List<String> tech);
}
