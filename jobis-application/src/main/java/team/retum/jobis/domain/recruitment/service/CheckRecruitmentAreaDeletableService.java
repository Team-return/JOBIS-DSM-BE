package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaCannotDeleteException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@Service
public class CheckRecruitmentAreaDeletableService {
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void checkRecruitmentAreaDeletable(RecruitArea recruitArea) {
        Long areaCount = queryRecruitmentPort.queryRecruitmentAreaCountByRecruitmentId(recruitArea.getRecruitmentId());
        if (areaCount <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }
    }
}
