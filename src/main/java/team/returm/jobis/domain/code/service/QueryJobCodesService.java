package team.returm.jobis.domain.code.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import team.returm.jobis.domain.code.domain.repository.CodeJpaRepository;
import team.returm.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryJobCodesService {
    private final CodeJpaRepository codeJpaRepository;

    @Transactional(readOnly = true)
    public List<JobCodeResponse> execute() {
        return codeJpaRepository.findAll().stream()
                .map(c -> JobCodeResponse.builder()
                        .code(c.getId())
                        .keyword(c.getKeyword())
                        .jobType(c.getJobType())
                        .build())
                .toList();
    }
}
