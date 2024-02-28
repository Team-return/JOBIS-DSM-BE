package team.retum.jobis.domain.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notice.presentation.dto.CreateNoticeWebRequest;
import team.retum.jobis.domain.notice.presentation.dto.UpdateNoticeWebRequest;
import team.retum.jobis.domain.notice.usecase.CreateNoticeUseCase;
import team.retum.jobis.domain.notice.usecase.UpdateNoticeUseCase;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeWebAdapter {

    private final CreateNoticeUseCase createNoticeUseCase;
    private final UpdateNoticeUseCase updateNoticeUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNotice(@RequestBody @Valid CreateNoticeWebRequest request) {
        createNoticeUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{notice-id}")
    public void updateNotice(
        @RequestBody @Valid UpdateNoticeWebRequest request,
        @PathVariable("notice-id") Long noticeId
    ) {
        updateNoticeUseCase.execute(request.getTitle(), request.getContent(), noticeId);
    }
}
