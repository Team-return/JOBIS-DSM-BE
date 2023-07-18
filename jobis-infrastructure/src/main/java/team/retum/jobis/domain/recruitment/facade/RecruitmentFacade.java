package team.retum.jobis.domain.recruitment.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import com.example.jobisapplication.domain.code.domain.CodeType;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.common.util.StringUtil;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RecruitmentFacade {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentEntity queryRecruitmentById(Long id) {
        return recruitmentRepository.queryRecruitmentById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    public void createRecruitArea(
            Map<CodeType, List<CodeEntity>> codes,
            RecruitmentEntity recruitmentEntity,
            String majorTask,
            int hiredCount
    ) {
        List<String> jobCodes = codes.get(CodeType.JOB).stream()
                .map(CodeEntity::getKeyword)
                .toList();

        RecruitAreaEntity recruitAreaEntity = recruitmentRepository.saveRecruitArea(
                RecruitAreaEntity.builder()
                        .majorTask(majorTask)
                        .hiredCount(hiredCount)
                        .recruitment(recruitmentEntity)
                        .jobCodes(StringUtil.joinStringList(jobCodes))
                        .build()
        );

        recruitmentRepository.saveAllRecruitAreaCodes(
                codes.get(CodeType.TECH).stream()
                        .map(code -> new RecruitAreaCodeEntity(recruitAreaEntity, code))
                        .toList()
        );
    }
}
