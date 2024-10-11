package team.retum.jobis.domain.intern.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.intern.model.WinterIntern;
import team.retum.jobis.domain.intern.persistence.entity.WinterInternEntity;
import team.retum.jobis.domain.intern.persistence.repository.WinterInternJpaRepository;
import team.retum.jobis.domain.intern.spi.WinterInternPort;

import static team.retum.jobis.domain.intern.persistence.entity.QWinterInternEntity.winterInternEntity;

@RequiredArgsConstructor
@Repository
public class WinterInternPersistenceAdapter implements WinterInternPort {

    private final JPAQueryFactory queryFactory;
    private final WinterInternJpaRepository winterInternJpaRepository;

    @Override
    public boolean getIsWinterIntern() {
        Boolean result = queryFactory
            .select(winterInternEntity.isWinterIntern)
            .from(winterInternEntity)
            .fetchFirst();

        return result != null ? result : false;
    }

    @Override
    public void save(WinterIntern winterIntern) {
        WinterInternEntity entity = winterInternJpaRepository.findTopByOrderById()
            .orElse(new WinterInternEntity(winterIntern.isWinterInterned()));

        entity.setWinterIntern(winterIntern.isWinterInterned());

        winterInternJpaRepository.save(entity);
    }
}
