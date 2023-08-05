package team.retum.jobis.common.spi;

public interface FeignClientPort {
    String getCompanyNameByBizNo(String businessNumber);
    boolean checkCompanyExistsByBizNo(String businessNumber);
}
