package team.retum.jobis.domain.notice.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.NoticeAttachment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeVO{

    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
}