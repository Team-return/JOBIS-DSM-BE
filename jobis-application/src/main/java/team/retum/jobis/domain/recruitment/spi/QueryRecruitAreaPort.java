package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.model.RecruitArea;

import java.util.List;

public interface QueryRecruitAreaPort {

    RecruitArea getByIdOrThrow(Long recruitAreaId);

    Long getCountByRecruitmentId(Long recruitmentId);

    List<RecruitAreaResponse> getByRecruitmentId(Long recruitmentId);
}
