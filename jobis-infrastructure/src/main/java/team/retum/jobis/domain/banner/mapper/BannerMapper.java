package team.retum.jobis.domain.banner.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.banner.entity.BannerEntity;
import team.retum.jobis.domain.banner.model.Banner;

@RequiredArgsConstructor
@Component
public class BannerMapper {

    public BannerEntity toEntity(Banner domain) {
        return BannerEntity.builder()
                .id(domain.getId())
                .bannerUrl(domain.getBannerUrl())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .build();
    }

    public Banner toDomain(BannerEntity entity) {
        return Banner.builder()
                .id(entity.getId())
                .bannerUrl(entity.getBannerUrl())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
