package team.returm.jobis.domain.code.service;

import java.util.List;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryJobCodesService {

    private final CodeRepository codeRepository;

    public List<JobCodeResponse> execute() {
        return codeRepository.queryJobCodes().stream()
                .map(c-> JobCodeResponse.builder()
                        .code(c.getId())
                        .keyword(c.getKeyword())
                        .jobType(c.getJobType())
                        .build())
                .toList();
    }
}
