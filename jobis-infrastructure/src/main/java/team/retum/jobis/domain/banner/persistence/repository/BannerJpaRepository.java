package team.retum.jobis.domain.banner.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.banner.persistence.entity.BannerEntity;

public interface BannerJpaRepository extends CrudRepository<BannerEntity, Long> {

}
