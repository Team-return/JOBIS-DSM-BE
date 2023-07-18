package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import com.example.jobisapplication.domain.code.domain.CodeType;
import team.retum.jobis.domain.code.facade.CodeFacade;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
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
public class CreateRecruitAreaService {

    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;

    public void execute(RecruitAreaRequest request, Long recruitmentId) {
        UserEntity userEntity = userFacade.getCurrentUser();

        RecruitmentEntity recruitmentEntity = recruitmentFacade.queryRecruitmentById(recruitmentId);
        if (userEntity.getAuthority() == Authority.COMPANY) {
            recruitmentEntity.checkCompany(userEntity.getId());
        }

        Map<CodeType, List<CodeEntity>> codes = codeFacade
                .queryCodesByIdIn(request.getCodes()).stream()
                .collect(Collectors.groupingBy(CodeEntity::getCodeType));

        recruitmentFacade.createRecruitArea(
                codes,
                recruitmentEntity,
                request.getMajorTask(),
                request.getHiring()
        );
    }
}
