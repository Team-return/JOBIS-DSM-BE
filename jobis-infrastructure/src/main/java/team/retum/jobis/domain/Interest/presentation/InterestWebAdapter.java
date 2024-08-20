package team.retum.jobis.domain.interest.presentation;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.retum.jobis.domain.interest.dto.InterestResponse;
import team.retum.jobis.domain.interest.dto.ToggleInterestRequest;
import team.retum.jobis.domain.interest.usecase.QueryMyInterestsUseCase;
import team.retum.jobis.domain.interest.usecase.ToggleInterestUseCase;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/interests")
@RestController
public class InterestWebAdapter {

    private final ToggleInterestUseCase toggleInterestUseCase;
    private final QueryMyInterestsUseCase queryMyInterestsUseCase;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void toggleInterest(@RequestBody @Valid ToggleInterestRequest request) {
        toggleInterestUseCase.execute(request.getCodes());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<InterestResponse> queryMyInterests() {
        return queryMyInterestsUseCase.execute();
    }
}
