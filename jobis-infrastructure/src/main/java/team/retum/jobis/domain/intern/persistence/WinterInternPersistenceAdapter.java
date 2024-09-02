package team.retum.jobis.domain.intern.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.intern.model.WinterIntern;
import team.retum.jobis.domain.intern.persistence.mapper.WinterInternMapper;
import team.retum.jobis.domain.intern.persistence.repository.WinterInternJpaRepository;
import team.retum.jobis.domain.intern.spi.WinterInternPort;

import static team.retum.jobis.domain.intern.persistence.entity.QWinterInternEntity.winterInternEntity;

@RequiredArgsConstructor
@Repository
public class WinterInternPersistenceAdapter implements WinterInternPort {

    private final JPAQueryFactory queryFactory;
    private final WinterInternJpaRepository winterInternJpaRepository;
    private final WinterInternMapper winterInternMapper;

    @Override
    public boolean getIsWintern() {
        Boolean result = queryFactory
            .select(winterInternEntity.isWinterIntern)
            .from(winterInternEntity)
            .fetchFirst();

        return result != null ? result : false;
    }

    @Override
    public void save(WinterIntern winterIntern) {
        winterInternMapper.toDomain(
            winterInternJpaRepository.save(
                winterInternMapper.toEntity(winterIntern)
            )
        );
    }
}
