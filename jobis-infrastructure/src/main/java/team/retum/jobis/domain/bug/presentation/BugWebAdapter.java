package team.retum.jobis.domain.bug.presentation;

import com.example.jobisapplication.domain.bug.dto.QueryBugReportDetailsResponse;
import com.example.jobisapplication.domain.bug.dto.QueryBugReportsResponse;
import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import com.example.jobisapplication.domain.bug.usecase.CreateBugReportUseCase;
import com.example.jobisapplication.domain.bug.usecase.QueryBugReportDetailsUseCase;
import com.example.jobisapplication.domain.bug.usecase.QueryBugReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.retum.jobis.domain.bug.presentation.dto.request.CreateBugReportWebRequest;

import javax.validation.Valid;

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
