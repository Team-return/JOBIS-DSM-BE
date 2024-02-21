package team.retum.jobis.domain.bookmark.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarkUserVO {
    private final Long recruitmentId;
    private final Long userId;
    private final String companyName;
    private final String token;
}
