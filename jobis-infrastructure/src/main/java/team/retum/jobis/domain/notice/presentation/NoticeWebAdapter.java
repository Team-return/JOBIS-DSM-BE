package team.retum.jobis.domain.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notice.dto.response.QueryNoticeDetailResponse;
import team.retum.jobis.domain.notice.dto.response.QueryNoticesResponse;
import team.retum.jobis.domain.notice.dto.response.QueryNoticeViewersResponse;
import team.retum.jobis.domain.notice.presentation.dto.CreateNoticeWebRequest;
import team.retum.jobis.domain.notice.presentation.dto.UpdateNoticeWebRequest;
import team.retum.jobis.domain.notice.usecase.CreateNoticeUseCase;
import team.retum.jobis.domain.notice.usecase.DeleteNoticeUseCase;
import team.retum.jobis.domain.notice.usecase.QueryNoticeDetailUseCase;
import team.retum.jobis.domain.notice.usecase.QueryNoticesUseCase;
import team.retum.jobis.domain.notice.usecase.QueryNoticeViewersUseCase;
import team.retum.jobis.domain.notice.usecase.RecordNoticeViewUseCase;
import team.retum.jobis.domain.notice.usecase.UpdateNoticeUseCase;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeWebAdapter {

    private final CreateNoticeUseCase createNoticeUseCase;
    private final UpdateNoticeUseCase updateNoticeUseCase;
    private final DeleteNoticeUseCase deleteNoticeUseCase;
    private final QueryNoticesUseCase queryNoticesUseCase;
    private final QueryNoticeDetailUseCase queryNoticeDetailsUseCase;
    private final RecordNoticeViewUseCase recordNoticeViewUseCase;
    private final QueryNoticeViewersUseCase queryNoticeViewersUseCase;

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

    @GetMapping("/{notice-id}")
    public QueryNoticeDetailResponse queryNoticeDetails(
        @PathVariable("notice-id") Long noticeId
    ) {
        return queryNoticeDetailsUseCase.execute(noticeId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{notice-id}/views")
    public void recordNoticeView(@PathVariable("notice-id") Long noticeId) {
        recordNoticeViewUseCase.execute(noticeId);
    }

    @GetMapping("/{notice-id}/viewers")
    public QueryNoticeViewersResponse queryNoticeViewers(
        @PathVariable("notice-id") Long noticeId
    ) {
        return queryNoticeViewersUseCase.execute(noticeId);
    }
}
