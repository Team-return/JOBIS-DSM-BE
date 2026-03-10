package team.retum.jobis.domain.banner.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.banner.exception.BannerNotFoundException;
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

import static team.retum.jobis.domain.banner.persistence.entity.QBannerEntity.bannerEntity;

@RequiredArgsConstructor
@Repository
public class BannerPersistenceAdapter implements BannerPort {

    private final BannerJpaRepository bannerJpaRepository;
    private final BannerMapper bannerMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(Banner banner) {
        bannerMapper.toDomain(
            bannerJpaRepository.save(
                bannerMapper.toEntity(banner)
            )
        );
    }

    @Override
    public void delete(Banner banner) {
        bannerJpaRepository.delete(bannerMapper.toEntity(banner));
    }

    @Override
    public void deleteExpiredBanners() {
        queryFactory
            .delete(bannerEntity)
            .where(bannerEntity.endDate.loe(LocalDate.now()))
            .execute();
    }

    @Override
    public Banner getByIdOrThrow(Long bannerId) {
        return bannerJpaRepository.findById(bannerId)
            .map(bannerMapper::toDomain)
            .orElseThrow(() -> BannerNotFoundException.EXCEPTION);
    }

    @Override
    public List<BannerVO> getCurrent() {
        LocalDate today = LocalDate.now();
        return queryFactory
            .select(
                new QQueryBannerVO(
                    bannerEntity.id,
                    bannerEntity.title,
                    bannerEntity.content,
                    bannerEntity.bannerType,
                    bannerEntity.detailId
                )
            )
            .from(bannerEntity)
            .where(
                bannerEntity.startDate.loe(today),
                bannerEntity.endDate.gt(today)
            )
            .stream()
            .map(BannerVO.class::cast)
            .toList();
    }

    @Override
    public List<TeacherBannersVO> getByIsOpened(boolean isOpened) {
        return queryFactory
            .select(
                new QQueryTeacherBannersVO(
                    bannerEntity.id,
                    bannerEntity.title,
                    bannerEntity.content,
                    bannerEntity.bannerType,
                    bannerEntity.startDate,
                    bannerEntity.endDate,
                    bannerEntity.detailId
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
            return bannerEntity.startDate.loe(today)
                .and(bannerEntity.endDate.gt(today));
        } else {
            return bannerEntity.startDate.gt(today);
        }
    }
}
