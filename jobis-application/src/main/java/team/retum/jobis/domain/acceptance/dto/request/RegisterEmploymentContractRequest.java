package team.retum.jobis.domain.acceptance.dto.request;

import java.util.List;

public record RegisterEmploymentContractRequest(
        List<String> codeKeywords,
        List<Long> applicationIds
) {}