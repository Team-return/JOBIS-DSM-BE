package team.retum.jobis.domain.banner.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.persistence.mapper.BannerMapper;
import team.retum.jobis.domain.banner.persistence.repository.BannerJpaRepository;
import team.retum.jobis.domain.banner.persistence.repository.vo.QQueryBannerVO;
import team.retum.jobis.domain.banner.persistence.repository.vo.QQueryTeacherBannersVO;
import team.retum.jobis.domain.banner.spi.BannerPort;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;
import team.retum.jobis.domain.banner.spi.vo.TeacherBannersVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.banner.persistence.entity.QBannerEntity.bannerEntity;


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
        LocalDate today = LocalDate.now();
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
                        bannerEntity.startDate.before(today),
                        bannerEntity.endDate.after(today)
                )
                .stream()
                .map(BannerVO.class::cast)
                .toList();
    }

    @Override
    public List<TeacherBannersVO> queryBanners(boolean isOpened) {
        return queryFactory
                .select(
                        new QQueryTeacherBannersVO(
                                bannerEntity.id,
                                bannerEntity.bannerUrl,
                                bannerEntity.bannerType,
                                bannerEntity.startDate,
                                bannerEntity.endDate
                        )
                )
                .from(bannerEntity)
                .where(checkStatus(isOpened))
                .stream()
                .map(TeacherBannersVO.class::cast)
                .toList();
    }

    private BooleanExpression checkStatus(boolean isOpened) {
        LocalDate today = LocalDate.now();
        if (isOpened) {
            return bannerEntity.startDate.before(today);
        } else {
            return bannerEntity.startDate.after(today);
        }
    }
}
