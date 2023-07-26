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
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.CommandRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateRecruitAreaUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryCodePort queryCodePort;
    private final SecurityPort securityPort;

    public void execute(CreateRecruitAreaRequest request, Long recruitmentId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        Authority currentUserAuthority = securityPort.getCurrentUserAuthority();
        if (currentUserAuthority == Authority.COMPANY) {
            recruitment.checkCompany(securityPort.getCurrentUserId());
        }

        String jobCodes =
                StringUtil.joinStringList(queryCodePort.queryCodesByIdIn(request.getJobCodes()));

        RecruitArea savedRecruitArea = commandRecruitmentPort.saveRecruitmentArea(
                RecruitArea.builder()
                        .recruitmentId(recruitment.getId())
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
