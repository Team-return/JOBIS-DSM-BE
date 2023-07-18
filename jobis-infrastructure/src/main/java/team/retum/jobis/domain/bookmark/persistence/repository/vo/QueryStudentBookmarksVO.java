package team.retum.jobis.domain.bookmark.persistence.repository.vo;

import com.example.jobisapplication.domain.bookmark.spi.vo.StudentBookmarksVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QueryStudentBookmarksVO extends StudentBookmarksVO {

    @QueryProjection
    public QueryStudentBookmarksVO(String companyName, Long recruitmentId, LocalDateTime createdAt) {
        super(companyName, recruitmentId, createdAt);
    }
}
