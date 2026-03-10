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
            .title(domain.getTitle())
            .content(domain.getContent())
            .bannerType(domain.getBannerType())
            .startDate(domain.getStartDate())
            .endDate(domain.getEndDate())
            .detailId(domain.getDetailId())
            .build();
    }

    public Banner toDomain(BannerEntity entity) {
        return Banner.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .bannerType(entity.getBannerType())
            .startDate(entity.getStartDate())
            .endDate(entity.getEndDate())
            .detailId(entity.getDetailId())
            .build();
    }
}
