package team.retum.jobis.domain.interview.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import team.retum.jobis.domain.interview.presentation.dto.InterviewWebRequest;
import team.retum.jobis.domain.interview.usecase.CreateInterviewUseCase;

@RestController
@RequestMapping("/interviews")
@RequiredArgsConstructor
public class InterviewWebAdapter {

    private final CreateInterviewUseCase createInterviewUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid InterviewWebRequest request) {
        createInterviewUseCase.execute(request.toDomainRequest());
    }
}
