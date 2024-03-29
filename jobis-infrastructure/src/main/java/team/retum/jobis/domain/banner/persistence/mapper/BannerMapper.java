package team.retum.jobis.domain.banner.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.persistence.entity.BannerEntity;

@RequiredArgsConstructor
@Component
public class BannerMapper {

    public BannerEntity toEntity(Banner domain) {
        return BannerEntity.builder()
                .id(domain.getId())
                .bannerUrl(domain.getBannerUrl())
                .bannerType(domain.getBannerType())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .build();
    }

    public Banner toDomain(BannerEntity entity) {
        return Banner.builder()
                .id(entity.getId())
                .bannerUrl(entity.getBannerUrl())
                .bannerType(entity.getBannerType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
