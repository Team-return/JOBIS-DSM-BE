package team.retum.jobis.domain.intern.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.intern.usecase.QueryIsWinterInternUseCase;
import team.retum.jobis.domain.intern.usecase.ToggleWinterInternUseCase;

@RequiredArgsConstructor
@RequestMapping("/winter-intern")
@RestController
public class WinterInternWebAdapter {

    private final QueryIsWinterInternUseCase queryIsWinterInternUseCase;
    private final ToggleWinterInternUseCase toggleWinterInternUseCase;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void toggleWinterIntern() {
        toggleWinterInternUseCase.execute();
    }

    @GetMapping
    public boolean getIsWinterIntern() {
        return queryIsWinterInternUseCase.execute();
    }
}
