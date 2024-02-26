package team.retum.jobis.domain.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notice.presentation.dto.CreateNoticeWebRequest;
import team.retum.jobis.domain.notice.usecase.CreateNoticeUseCase;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeWebAdapter {

    private final CreateNoticeUseCase createNoticeUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNotice(@RequestBody @Valid CreateNoticeWebRequest request) {
        createNoticeUseCase.execute(request.toDomainRequest());
    }
}
