package team.retum.jobis.domain.banner.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;
import team.retum.jobis.domain.banner.spi.vo.TeacherBannersVO;

import java.time.LocalDate;

@Getter
public class QueryTeacherBannersVO extends TeacherBannersVO {

    @QueryProjection
    public QueryTeacherBannersVO(Long bannerId, String title, String content, BannerType bannerType, LocalDate startDate, LocalDate endTime, Long detailId) {
        super(bannerId, title, content, bannerType, startDate, endTime, detailId);
    }
}
