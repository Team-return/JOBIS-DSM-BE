package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.returm.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UpdateRecruitAreaService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;
    private final RecruitmentFacade recruitmentFacade;

    public void execute(RecruitAreaRequest request, Long recruitAreaId) {
        User user = userFacade.getCurrentUser();

        RecruitArea recruitArea = recruitmentRepository.queryRecruitAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        if (user.getAuthority() == Authority.COMPANY) {
            recruitArea.getRecruitment().checkCompany(user.getId());
        }

        recruitmentRepository.deleteRecruitAreaCodeByRecruitAreaId(recruitArea.getId());

        Map<CodeType, List<Code>> codes = codeFacade
                .queryCodesByIdIn(request.getCodes()).stream()
                .collect(Collectors.groupingBy(Code::getCodeType));

        recruitmentFacade.createRecruitArea(
                codes,
                recruitArea.getRecruitment(),
                request.getMajorTask(),
                request.getHiring()
        );
    }
}