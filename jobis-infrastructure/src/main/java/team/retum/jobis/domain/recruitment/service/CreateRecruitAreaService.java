package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.Code;
import team.retum.jobis.domain.code.persistence.enums.CodeType;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;
import team.retum.jobis.domain.persistence.domain.User;
import team.retum.jobis.domain.persistence.domain.enums.Authority;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateRecruitAreaService {

    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;

    public void execute(RecruitAreaRequest request, Long recruitmentId) {
        User user = userFacade.getCurrentUser();

        Recruitment recruitment = recruitmentFacade.queryRecruitmentById(recruitmentId);
        if (user.getAuthority() == Authority.COMPANY) {
            recruitment.checkCompany(user.getId());
        }

        Map<CodeType, List<Code>> codes = codeFacade
                .queryCodesByIdIn(request.getCodes()).stream()
                .collect(Collectors.groupingBy(Code::getCodeType));

        recruitmentFacade.createRecruitArea(
                codes,
                recruitment,
                request.getMajorTask(),
                request.getHiring()
        );
    }
}
