package team.returm.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.JobCodesResponse;
import team.returm.jobis.domain.code.presentation.dto.response.JobCodesResponse.JobCodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryJobCodesService {

    private final CodeRepository codeRepository;

    public JobCodesResponse execute() {
        List<JobCodeResponse> codes = codeRepository.queryJobCodes().stream()
                .map(code -> JobCodeResponse.builder()
                        .code(code.getId())
                        .keyword(code.getKeyword())
                        .jobType(code.getJobType())
                        .build())
                .toList();

        return new JobCodesResponse(codes);
    }
}