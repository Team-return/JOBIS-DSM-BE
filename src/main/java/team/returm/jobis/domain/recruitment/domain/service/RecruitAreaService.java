package team.returm.jobis.domain.recruitment.domain.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecruitAreaService {

    private final CodeFacade codeFacade;
    private final RecruitmentRepository recruitmentRepository;

    public void createRecruitArea(RecruitAreaRequest request, Recruitment recruitment) {
        Map<CodeType, List<Code>> codes = codeFacade
                .queryCodesByIdIn(request.getCodes()).stream()
                .collect(Collectors.groupingBy(Code::getCodeType));

        List<String> jobCodes = codes.get(CodeType.JOB).stream()
                .map(Code::getKeyword)
                .toList();

        RecruitArea recruitArea = recruitmentRepository.saveRecruitArea(
                RecruitArea.builder()
                        .majorTask(request.getMajorTask())
                        .hiredCount(request.getHiring())
                        .recruitment(recruitment)
                        .jobCodes(StringUtil.joinStringList(jobCodes))
                        .build()
        );

        recruitmentRepository.saveAllRecruitAreaCodes(
                codeFacade.generateRecruitAreaCode(recruitArea, codes.get(CodeType.TECH))
        );
    }
}
