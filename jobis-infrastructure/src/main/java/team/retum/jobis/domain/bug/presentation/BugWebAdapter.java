package team.retum.jobis.domain.bug.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.bug.dto.response.QueryBugReportDetailsResponse;
import team.retum.jobis.domain.bug.dto.response.QueryBugReportsResponse;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.presentation.dto.request.CreateBugReportWebRequest;
import team.retum.jobis.domain.bug.usecase.CreateBugReportUseCase;
import team.retum.jobis.domain.bug.usecase.QueryBugReportDetailsUseCase;
import team.retum.jobis.domain.bug.usecase.QueryBugReportsUseCase;

@RequiredArgsConstructor
@RequestMapping("/bugs")
@RestController
public class BugWebAdapter {

    private final CreateBugReportUseCase createBugReportUseCase;
    private final QueryBugReportsUseCase queryBugReportsUseCase;
    private final QueryBugReportDetailsUseCase queryBugReportDetailsUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBugReport(@RequestBody @Valid CreateBugReportWebRequest request) {
        createBugReportUseCase.execute(request.toDomainRequest());
    }

    @GetMapping
    public QueryBugReportsResponse queryBugReports(
        @RequestParam(value = "development_area", required = false) DevelopmentArea developmentArea
    ) {
        return queryBugReportsUseCase.execute(developmentArea);
    }

    @GetMapping("/{bug-report-id}")
    public QueryBugReportDetailsResponse queryBugReportDetails(
        @PathVariable("bug-report-id") Long bugReportId
    ) {
        return queryBugReportDetailsUseCase.execute(bugReportId);
    }
}
