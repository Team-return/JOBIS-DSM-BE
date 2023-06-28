package team.retum.jobis.domain.recruitment.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.domain.Code;
import team.retum.jobis.domain.code.domain.RecruitAreaCode;
import team.retum.jobis.domain.code.domain.enums.CodeType;
import team.retum.jobis.domain.recruitment.domain.RecruitArea;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.global.util.StringUtil;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RecruitmentFacade {

    private final RecruitmentRepository recruitmentRepository;

    public Recruitment queryRecruitmentById(Long id) {
        return recruitmentRepository.queryRecruitmentById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    public void createRecruitArea(
            Map<CodeType, List<Code>> codes,
            Recruitment recruitment,
            String majorTask,
            int hiredCount
    ) {
        List<String> jobCodes = codes.get(CodeType.JOB).stream()
                .map(Code::getKeyword)
                .toList();

        RecruitArea recruitArea = recruitmentRepository.saveRecruitArea(
                RecruitArea.builder()
                        .majorTask(majorTask)
                        .hiredCount(hiredCount)
                        .recruitment(recruitment)
                        .jobCodes(StringUtil.joinStringList(jobCodes))
                        .build()
        );

        recruitmentRepository.saveAllRecruitAreaCodes(
                codes.get(CodeType.TECH).stream()
                        .map(code -> new RecruitAreaCode(recruitArea, code))
                        .toList()
        );
    }
}
