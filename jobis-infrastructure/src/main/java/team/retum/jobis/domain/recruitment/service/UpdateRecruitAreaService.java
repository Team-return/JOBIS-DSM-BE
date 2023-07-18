package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import com.example.jobisapplication.domain.code.domain.CodeType;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import com.example.jobisapplication.domain.auth.domain.Authority;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

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
        UserEntity userEntity = userFacade.getCurrentUser();

        RecruitAreaEntity recruitAreaEntity = recruitmentRepository.queryRecruitAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        if (userEntity.getAuthority() == Authority.COMPANY) {
            recruitAreaEntity.getRecruitmentEntity().checkCompany(userEntity.getId());
        }

        recruitmentRepository.deleteRecruitAreaCodeByRecruitAreaId(recruitAreaEntity.getId());

        Map<CodeType, List<CodeEntity>> codes = codeFacade
                .queryCodesByIdIn(request.getCodes()).stream()
                .collect(Collectors.groupingBy(CodeEntity::getCodeType));

        recruitmentFacade.createRecruitArea(
                codes,
                recruitAreaEntity.getRecruitmentEntity(),
                request.getMajorTask(),
                request.getHiring()
        );
    }
}