package team.retum.jobis.domain.recruitment.service;

import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaCannotDeleteException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@Service
public class CheckRecruitmentAreaDeletableService {

    public void checkRecruitmentAreaDeletable(RecruitArea recruitArea, QueryRecruitmentPort queryRecruitmentPort) {
        Long areaCount = queryRecruitmentPort.queryRecruitmentAreaCountByRecruitmentId(recruitArea.getRecruitmentId());
        if (areaCount <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }
    }
}
