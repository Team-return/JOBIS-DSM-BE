package team.retum.jobis.domain.bookmark.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.bookmark.spi.vo.BookmarkUserVO;

@Getter
public class QueryBookmarkUserVO extends BookmarkUserVO {

    @QueryProjection
    public QueryBookmarkUserVO(Long recruitmentId, Long userId, String companyName, String token) {
        super(recruitmentId, userId, companyName, token);
    }
}
