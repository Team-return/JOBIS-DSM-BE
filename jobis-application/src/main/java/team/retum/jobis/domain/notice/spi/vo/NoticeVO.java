package team.retum.jobis.domain.notice.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NoticeVO{

    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
}