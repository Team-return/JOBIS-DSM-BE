package team.retum.jobis.domain.banner.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.banner.entity.BannerEntity;
import team.retum.jobis.domain.banner.model.Banner;

public interface BannerJpaRepository extends CrudRepository<BannerEntity, Long> {
}
