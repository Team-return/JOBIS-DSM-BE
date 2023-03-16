package team.returm.jobis.domain.code.service;

import team.returm.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import team.returm.jobis.domain.code.domain.repository.CodeJpaRepository;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class FindJobCodeService {
    private final CodeJpaRepository codeJpaRepository;

    @Transactional(readOnly = true)
    public List<JobCodeResponse> execute() {
        return codeJpaRepository.findAllJobCode().stream()
                .map(c-> JobCodeResponse.builder()
                        .code(c.getId())
                        .keyword(c.getKeyword())
                        .jobType(c.getJobType())
                        .build())
                .toList();
    }
}
