package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.model.RecruitAreaCode;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaveRecruitmentAreaService {

    private final CommandRecruitmentPort commandRecruitmentPort;

    public void execute(CreateRecruitAreaRequest request, Long recruitmentId) {
        RecruitArea savedRecruitArea = commandRecruitmentPort.saveRecruitmentArea(
                RecruitArea.builder()
                        .recruitmentId(recruitmentId)
                        .hiredCount(request.getHiring())
                        .majorTask(request.getMajorTask())
                        .preferentialTreatment(request.getPreferentialTreatment())
                        .build()
        );

        List<RecruitAreaCode> recruitAreaCodes = new ArrayList<>();
        recruitAreaCodes.addAll(request.getTechCodes().stream()
                .map(
                        code -> RecruitAreaCode.builder()
                                .recruitAreaId(savedRecruitArea.getId())
                                .code(code)
                                .type(CodeType.TECH)
                                .build()
                ).toList()
        );
        recruitAreaCodes.addAll(request.getJobCodes().stream()
                .map(
                        code -> RecruitAreaCode.builder()
                                .recruitAreaId(savedRecruitArea.getId())
                                .code(code)
                                .type(CodeType.JOB)
                                .build()
                ).toList()
        );

        commandRecruitmentPort.saveAllRecruitmentAreaCodes(recruitAreaCodes);
    }
}
