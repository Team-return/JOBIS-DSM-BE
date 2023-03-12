package com.example.jobis.domain.code.service;

import com.example.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class FindJobCodeService {
    private final CodeRepository codeRepository;

    @Transactional(readOnly = true)
    public List<JobCodeResponse> execute() {
        return codeRepository.findAllJobCode().stream()
                .map(c-> JobCodeResponse.builder()
                        .code(c.getId())
                        .keyword(c.getKeyword())
                        .jobType(c.getJobType())
                        .build())
                .toList();
    }
}
