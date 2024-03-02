package team.retum.jobis.domain.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;
import team.retum.jobis.domain.banner.spi.vo.TeacherBannersVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBannerListResponse {

    private final List<TeacherBannersVO> banners;
}
