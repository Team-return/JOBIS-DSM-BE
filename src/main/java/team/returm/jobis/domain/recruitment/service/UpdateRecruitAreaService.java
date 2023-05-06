package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.domain.service.RecruitAreaService;
import team.returm.jobis.domain.recruitment.facade.RecruitAreaFacade;
import team.returm.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class UpdateRecruitAreaService {

    private final RecruitAreaFacade recruitAreaFacade;
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final RecruitAreaService recruitAreaService;

    public void execute(RecruitAreaRequest request, Long recruitAreaId) {
        User user = userFacade.getCurrentUser();

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaById(recruitAreaId);

        if (user.getAuthority() == Authority.COMPANY) {
            recruitArea.getRecruitment().checkCompany(user.getId());
        }

        recruitmentRepository.deleteRecruitAreaCodeByRecruitAreaId(recruitArea.getId());

        recruitAreaService.createRecruitArea(request, recruitArea.getRecruitment());
    }
}
