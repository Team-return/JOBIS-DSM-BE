package team.returm.jobis.domain.recruitment.facade;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.SubCode;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitAreaJpaRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RecruitAreaFacade {

    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final CodeFacade codeFacade;

    public RecruitArea getRecruitAreaById(Long id) {
        return recruitAreaJpaRepository.findById(id)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
    }

    public List<RecruitAreaCode> addRecruitAreaCodes(
            RecruitArea recruitArea,
            List<Code> codes,
            List<SubCode> subCodes
    ) {
        List<RecruitAreaCode> recruitAreaCodes = new ArrayList<>();

        recruitAreaCodes.addAll(codeFacade.generateRecruitAreaCodeByCode(recruitArea, codes));
        recruitAreaCodes.addAll(codeFacade.generateRecruitAreaCodeBySubCode(recruitArea, subCodes));
        return recruitAreaCodes;
    }
}
