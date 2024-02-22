package team.retum.jobis.domain.banner.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.persistence.mapper.BannerMapper;
import team.retum.jobis.domain.banner.persistence.repository.BannerJpaRepository;
import team.retum.jobis.domain.banner.spi.BannerPort;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BannerPersistenceAdapter implements BannerPort {

    private final BannerJpaRepository bannerJpaRepository;
    private final BannerMapper bannerMapper;

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
}
