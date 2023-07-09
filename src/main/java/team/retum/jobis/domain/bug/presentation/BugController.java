package team.retum.jobis.domain.bug.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.bug.presentation.dto.request.CreateBugReportRequest;
import team.retum.jobis.domain.bug.presentation.dto.response.QueryBugReportResponse;
import team.retum.jobis.domain.bug.service.CreateBugReportService;
import team.retum.jobis.domain.bug.service.QueryBugReportService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/bugs")
@RestController
public class BugController {

    private final CreateBugReportService createBugReportService;
    private final QueryBugReportService queryBugReportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBugReport(@RequestBody @Valid CreateBugReportRequest request) {
        createBugReportService.execute(request);
    }

    @GetMapping
    public QueryBugReportResponse queryBugReport() {
        return queryBugReportService.execute();
    }
}
