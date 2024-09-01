package team.retum.jobis.domain.wintern.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.wintern.model.Wintern;
import team.retum.jobis.domain.wintern.persistence.mapper.WinternMapper;
import team.retum.jobis.domain.wintern.persistence.repository.WinternJpaRepository;
import team.retum.jobis.domain.wintern.spi.WinternPort;

import static team.retum.jobis.domain.wintern.persistence.entity.QWinternEntity.winternEntity;

@RequiredArgsConstructor
@Repository
public class WinternPersistenceAdapter implements WinternPort {

    private final JPAQueryFactory queryFactory;
    private final WinternJpaRepository winternJpaRepository;
    private final WinternMapper winternMapper;

    @Override
    public boolean getIsWintern() {
        Boolean result = queryFactory
            .select(winternEntity.isWinterIntern)
            .from(winternEntity)
            .fetchFirst();

        return result != null ? result : false;
    }

    @Override
    public void save(Wintern wintern) {
        winternMapper.toDomain(
            winternJpaRepository.save(
                winternMapper.toEntity(wintern)
            )
        );
    }
}
