package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.common.util.StringUtil;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.RecruitAreaCode;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

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
                StringUtil.joinStringList(
                        queryCodePort.queryCodesByIdIn(request.getJobCodes()).stream()
                                .map(Code::getKeyword).toList()
                );

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
