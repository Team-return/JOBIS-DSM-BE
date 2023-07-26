package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.common.util.StringUtil;
import com.example.jobisapplication.domain.auth.model.Authority;
import com.example.jobisapplication.domain.code.model.RecruitAreaCode;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import com.example.jobisapplication.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import com.example.jobisapplication.domain.recruitment.exception.RecruitAreaNotFoundException;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import com.example.jobisapplication.domain.recruitment.spi.CommandRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class UpdateRecruitAreaUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryCodePort queryCodePort;

    public void execute(CreateRecruitAreaRequest request, Long recruitAreaId) {
        RecruitArea recruitArea = queryRecruitmentPort.queryRecruitmentAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        Authority currentUserAuthority = securityPort.getCurrentUserAuthority();
        if (currentUserAuthority == Authority.COMPANY) {
            Long currentUserId = securityPort.getCurrentUserId();
            queryRecruitmentPort.queryRecruitmentById(recruitArea.getRecruitmentId())
                    .get().checkCompany(currentUserId);
        }

        commandRecruitmentPort.deleteRecruitAreaById(recruitArea.getId());

        String jobCodes =
                StringUtil.joinStringList(queryCodePort.queryCodesByIdIn(request.getJobCodes()));

        RecruitArea savedRecruitArea = commandRecruitmentPort.saveRecruitmentArea(
                RecruitArea.builder()
                        .recruitmentId(recruitArea.getRecruitmentId())
                        .hiredCount(request.getHiring())
                        .jobCodes(jobCodes)
                        .majorTask(request.getMajorTask())
                        .build()
        );
        List<RecruitAreaCode> recruitAreaCodes = request.getTechCodes().stream()
                .map(
                        code -> RecruitAreaCode.builder()
                                .recruitAreaId(savedRecruitArea.getId())
                                .codeId(code)
                                .build()
                ).toList();
        commandRecruitmentPort.saveAllRecruitmentAreaCodes(recruitAreaCodes);
    }
}