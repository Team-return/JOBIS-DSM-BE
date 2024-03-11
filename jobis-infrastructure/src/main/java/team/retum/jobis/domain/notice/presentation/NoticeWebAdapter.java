package team.retum.jobis.domain.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.retum.jobis.domain.notice.dto.response.QueryNoticesResponse;
import team.retum.jobis.domain.notice.presentation.dto.CreateNoticeWebRequest;
import team.retum.jobis.domain.notice.presentation.dto.UpdateNoticeWebRequest;
import team.retum.jobis.domain.notice.usecase.*;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeWebAdapter {

    private final CreateNoticeUseCase createNoticeUseCase;
    private final UpdateNoticeUseCase updateNoticeUseCase;
    private final DeleteNoticeUseCase deleteNoticeUseCase;
    private final QueryNoticesUseCase queryNoticesUseCase;

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    public void deleteNotice(@PathVariable("notice-id") Long noticeId) {
        deleteNoticeUseCase.execute(noticeId);
    }

    @GetMapping
    public QueryNoticesResponse queryNotices() {
        return queryNoticesUseCase.execute();
    }
}
