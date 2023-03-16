package team.returm.jobis.domain.recruitment.service;

import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.facade.RecruitFacade;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChangeRecruitmentStatusService {
    private final RecruitFacade recruitFacade;

    public void execute(UUID id, RecruitStatus status) {
        Recruitment recruitment = recruitFacade.queryRecruitmentById(id);
        recruitment.changeStatus(status);
    }
}
