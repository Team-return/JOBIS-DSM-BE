package team.retum.jobis.domain.banner.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.persistence.mapper.BannerMapper;
import team.retum.jobis.domain.banner.persistence.repository.BannerJpaRepository;
import team.retum.jobis.domain.banner.persistence.repository.vo.QQueryBannerVO;
import team.retum.jobis.domain.banner.spi.BannerPort;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.bookmark.persistence.entity.QBannerEntity.bannerEntity;

@RequiredArgsConstructor
@Repository
public class BannerPersistenceAdapter implements BannerPort {

    private final BannerJpaRepository bannerJpaRepository;
    private final BannerMapper bannerMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public void saveBanner(Banner banner) {
        bannerMapper.toDomain(
                bannerJpaRepository.save(
                        bannerMapper.toEntity(banner)
                )
        );
    }

    @Override
    public void deleteBanner(Banner banner) {
        bannerJpaRepository.delete(bannerMapper.toEntity(banner));
    }

    @Override
    public Optional<Banner> queryBannerById(Long bannerId) {
        return bannerJpaRepository.findById(bannerId)
                .map(bannerMapper::toDomain);
    }

    @Override
    public List<BannerVO> queryCurrentBanners() {
        return queryFactory
                .select(
                        new QQueryBannerVO(
                                bannerEntity.id,
                                bannerEntity.bannerUrl,
                                bannerEntity.bannerType
                        )
                )
                .from(bannerEntity)
                .where(
                        bannerEntity.startDate.before(LocalDateTime.now()),
                        bannerEntity.endDate.after(LocalDateTime.now())
                )
                .fetch();
    }
}
