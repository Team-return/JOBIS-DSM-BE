package team.retum.jobis.domain.notification.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notification.dto.response.QueryTopicsResponse;
import team.retum.jobis.domain.notification.usecase.QueryTopicsUseCase;

@RequiredArgsConstructor
@RequestMapping("/topics")
@RestController
public class TopicSubscriptionWebAdapter {

    private final QueryTopicsUseCase queryTopicsUseCase;

    @GetMapping
    public QueryTopicsResponse queryTopics() {
        return queryTopicsUseCase.execute();
    }
}
