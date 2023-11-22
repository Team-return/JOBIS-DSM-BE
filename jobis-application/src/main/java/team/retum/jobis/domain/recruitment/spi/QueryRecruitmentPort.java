package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentVO;

import java.util.List;
import java.util.Optional;

public interface QueryRecruitmentPort {
    boolean existsOnRecruitmentByCompanyId(Long companyId, boolean winterIntern);

    List<Recruitment> queryAllRecruitments();

    Optional<Recruitment> queryRecruitmentById(Long recruitmentId);

    Optional<RecruitArea> queryRecruitmentAreaById(Long recruitAreaId);

    Long queryRecruitmentAreaCountByRecruitmentId(Long recruitmentId);

    Optional<Recruitment> queryRecentRecruitmentByCompanyId(Long companyId);

    RecruitmentDetailVO queryRecruitmentDetailById(Long recruitmentId);

    List<RecruitmentVO> queryRecruitmentsByFilter(RecruitmentFilter filter);

    List<Recruitment> queryRecruitmentsByIdIn(List<Long> recruitmentIds);

    Long getRecruitmentCountByFilter(RecruitmentFilter filter);

    List<RecruitAreaResponse> queryRecruitAreasByRecruitmentId(Long recruitmentId);

}
