package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.RecruitArea;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.persistence.domain.User;
import team.retum.jobis.domain.persistence.domain.enums.Authority;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

@Service
@RequiredArgsConstructor
public class DeleteRecruitAreaService {

    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public void execute(Long recruitAreaId) {
        User user = userFacade.getCurrentUser();

        RecruitArea recruitArea = recruitmentRepository.queryRecruitAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
        Recruitment recruitment = recruitArea.getRecruitment();

        if (recruitment.getRecruitAreas().size() <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }

        if (user.getAuthority() == Authority.COMPANY) {
            recruitment.checkCompany(user.getId());
        }

        recruitmentRepository.deleteRecruitAreaById(recruitArea.getId());
    }
}
