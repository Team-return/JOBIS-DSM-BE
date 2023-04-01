package team.returm.jobis.domain.code.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.code.presentation.dto.response.CodeResponse;
import team.returm.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import team.returm.jobis.domain.code.service.QueryJobCodesService;
import team.returm.jobis.domain.code.service.QueryTechCodesService;

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
