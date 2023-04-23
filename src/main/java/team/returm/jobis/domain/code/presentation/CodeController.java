package team.returm.jobis.domain.code.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.returm.jobis.domain.code.presentation.dto.response.JobCodesResponse;
import team.returm.jobis.domain.code.service.QueryBusinessAreaCodesService;
import team.returm.jobis.domain.code.service.QueryJobCodesService;
import team.returm.jobis.domain.code.service.QueryTechCodesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController {

    private final QueryTechCodesService queryTechCodesService;
    private final QueryJobCodesService queryJobCodesService;
    private final QueryBusinessAreaCodesService queryBusinessAreaCodesService;

    @GetMapping("/tech")
    public CodesResponse findTechCode(
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        return queryTechCodesService.execute(keyword);
    }

    @GetMapping("/job")
    public JobCodesResponse findAllJobCodes() {
        return queryJobCodesService.execute();
    }

    @GetMapping("/business-area")
    public CodesResponse findBusinessAreaCodes(
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        return queryBusinessAreaCodesService.execute(keyword);
    }
}
