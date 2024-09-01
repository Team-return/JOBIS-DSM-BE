package team.retum.jobis.domain.wintern.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.retum.jobis.domain.wintern.usecase.QueryIsWinternUseCase;
import team.retum.jobis.domain.wintern.usecase.ToggleWinternUseCase;

@RequiredArgsConstructor
@RequestMapping("/winterns")
@RestController
public class WinternWebAdapter {

    private final QueryIsWinternUseCase queryIsWinternUseCase;
    private final ToggleWinternUseCase toggleWinternUseCase;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void toggleWintern() {
        toggleWinternUseCase.execute();
    }

    @GetMapping
    public boolean getIsWintern() {
        return queryIsWinternUseCase.execute();
    }
}
