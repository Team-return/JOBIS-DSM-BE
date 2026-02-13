package team.retum.jobis.domain.interview.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.interview.dto.response.QueryInterviewsResponse;
import team.retum.jobis.domain.interview.presentation.dto.InterviewWebRequest;
import team.retum.jobis.domain.interview.usecase.CreateInterviewUseCase;
import team.retum.jobis.domain.interview.usecase.QueryInterviewsUseCase;
import team.retum.jobis.domain.recruitment.model.ProgressType;

@RequiredArgsConstructor
@RequestMapping("/interviews")
@RestController
public class InterviewWebAdapter {

    private final CreateInterviewUseCase createInterviewUseCase;
    private final QueryInterviewsUseCase queryInterviewsUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid InterviewWebRequest request) {
        createInterviewUseCase.execute(request.toDomainRequest());
    }

    @GetMapping
    public QueryInterviewsResponse queryInterviews(
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "month", required = false) Integer month,
        @RequestParam(value = "company_name", required = false) String companyName,
        @RequestParam(value = "interview_type", required = false) ProgressType interviewType
    ) {
        return queryInterviewsUseCase.execute(year, month, companyName, interviewType);
    }
}
