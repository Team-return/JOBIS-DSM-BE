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
    public QueryInterviewsResponse getInterviews(
        @RequestParam(required = false) Integer year,
        @RequestParam(required = false) Integer month
    ) {
        return queryInterviewsUseCase.execute(year, month);
    }
}
