package team.returm.jobis.domain.recruitment.service;

import team.returm.jobis.domain.recruitment.presentation.dto.request.CreateRecruitAreaRequest;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitAreaJpaRepository;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.facade.RecruitFacade;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CreateRecruitAreaService {

    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final CodeFacade codeFacade;
    private final RecruitFacade recruitFacade;
    private final UserFacade userFacade;

    public void execute(CreateRecruitAreaRequest request, UUID recruitmentId) {
        User user = userFacade.getCurrentUser();

        Recruitment recruitment = recruitFacade.queryRecruitmentById(recruitmentId);
        if(user.getAuthority() == Authority.COMPANY) {
            recruitment.checkCompany(recruitment.getId());
        }

        RecruitArea recruitArea = recruitAreaJpaRepository.save(
                RecruitArea.builder()
                        .majorTask(request.getMajorTask())
                        .hiredCount(request.getHiring())
                        .recruitment(recruitment)
                        .build()
        );

        List<Code> codes = codeFacade.findAllCodeById(
                Stream.of(request.getJobCodes(), request.getTechCodes())
                        .flatMap(Collection::stream)
                        .toList()
        );

        recruitmentRepository.saveAllRecruitAreaCodes(
                codeFacade.generateRecruitAreaCode(recruitArea, codes)
        );
    }
}
