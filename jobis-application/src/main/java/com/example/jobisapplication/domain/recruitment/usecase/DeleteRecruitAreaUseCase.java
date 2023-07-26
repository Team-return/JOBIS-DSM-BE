package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.auth.model.Authority;
import com.example.jobisapplication.domain.recruitment.exception.RecruitAreaCannotDeleteException;
import com.example.jobisapplication.domain.recruitment.exception.RecruitAreaNotFoundException;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import com.example.jobisapplication.domain.recruitment.spi.CommandRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class DeleteRecruitAreaUseCase {

    private final SecurityPort securityPort;
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute(Long recruitAreaId) {
        RecruitArea recruitArea = queryRecruitmentPort.queryRecruitmentAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        Authority currentUserAuthority = securityPort.getCurrentUserAuthority();
        if (currentUserAuthority == Authority.COMPANY) {
            Long currentUserId = securityPort.getCurrentUserId();
            queryRecruitmentPort.queryRecruitmentById(recruitArea.getRecruitmentId())
                    .get().checkCompany(currentUserId);
        }

        Long recruitAreaCount =
                queryRecruitmentPort.queryRecruitmentAreaCountByRecruitmentId(recruitArea.getRecruitmentId());
        if (recruitAreaCount <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }

        commandRecruitmentPort.deleteRecruitAreaById(recruitArea.getId());
    }
}
