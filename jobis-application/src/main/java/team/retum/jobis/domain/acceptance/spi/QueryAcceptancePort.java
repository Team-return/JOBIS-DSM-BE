package team.retum.jobis.domain.acceptance.spi;

import team.retum.jobis.domain.acceptance.spi.vo.AcceptanceVO;

import java.util.List;

public interface QueryAcceptancePort {

    List<AcceptanceVO> getByCompanyIdAndYear(Long companyId, Integer year);
}
