package team.returm.jobis.domain.code.presentation;

import team.returm.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import team.returm.jobis.domain.code.presentation.dto.response.CodeResponse;
import team.returm.jobis.domain.code.service.QueryTechCodesService;
import team.returm.jobis.domain.code.service.QueryJobCodesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController {
    private final QueryTechCodesService queryTechCodesService;
    private final QueryJobCodesService queryJobCodesService;

    @GetMapping("/tech")
    public List<CodeResponse> findTechCode(@RequestParam(value = "keyword", required = false) String keyword) {
        return queryTechCodesService.execute(keyword);
    }

    @GetMapping("/job")
    public List<JobCodeResponse> findAllJobCodes() {
        return queryJobCodesService.execute();
    }

}
