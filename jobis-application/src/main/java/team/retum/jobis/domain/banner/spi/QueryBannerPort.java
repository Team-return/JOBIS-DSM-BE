package team.retum.jobis.domain.banner.spi;

import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;
import team.retum.jobis.domain.banner.spi.vo.TeacherBannersVO;

import java.util.List;

public interface QueryBannerPort {

    Banner getByIdOrThrow(Long bannerId);

    List<BannerVO> getCurrentBanners();

    List<TeacherBannersVO> getByIsOpened(boolean isOpened);
}
