package team.retum.jobis.domain.Interest.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import team.retum.jobis.domain.interest.usecase.ToggleInterestUseCase;

@RequiredArgsConstructor
@RequestMapping("/interests")
@RestController
public class InterestWebAdapter {

    private final ToggleInterestUseCase toggleInterestUseCase;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{code}")
    public void toggleInterest(@PathVariable("code") Long code) {
        toggleInterestUseCase.execute(code);
    }
}
