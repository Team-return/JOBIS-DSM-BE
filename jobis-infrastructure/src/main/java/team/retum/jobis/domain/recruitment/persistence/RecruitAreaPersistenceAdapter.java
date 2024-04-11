package team.retum.jobis.domain.recruitment.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.persistence.mapper.RecruitAreaMapper;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitAreaJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.vo.QQueryRecruitAreaVO;
import team.retum.jobis.domain.recruitment.spi.RecruitAreaPort;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;
import static team.retum.jobis.domain.code.persistence.entity.QRecruitAreaCodeEntity.recruitAreaCodeEntity;
import static team.retum.jobis.domain.recruitment.persistence.entity.QRecruitAreaEntity.recruitAreaEntity;

@Component
@RequiredArgsConstructor
public class RecruitAreaPersistenceAdapter implements RecruitAreaPort {

    private final JPAQueryFactory queryFactory;
    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final RecruitAreaMapper recruitAreaMapper;

    @Override
    public RecruitArea save(RecruitArea recruitArea) {
        return recruitAreaMapper.toDomain(
            recruitAreaJpaRepository.save(recruitAreaMapper.toEntity(recruitArea))
        );
    }

    @Override
    public void saveAll(List<RecruitArea> recruitAreas) {
        recruitAreaJpaRepository.saveAll(
            recruitAreas.stream()
                .map(recruitAreaMapper::toEntity).toList()
        );
    }

    @Override
    public void delete(RecruitArea recruitArea) {
        recruitAreaJpaRepository.delete(
            recruitAreaMapper.toEntity(recruitArea)
        );
    }

    @Override
    public RecruitArea getByIdOrThrow(Long recruitAreaId) {
        return recruitAreaMapper.toDomain(
            recruitAreaJpaRepository.findById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION)
        );
    }

    @Override
    public Long getCountByRecruitmentId(Long recruitmentId) {
        return queryFactory
            .select(recruitAreaEntity.count())
            .from(recruitAreaEntity)
            .where(recruitAreaEntity.recruitment.id.eq(recruitmentId))
            .fetchOne();
    }

    @Override
    public List<RecruitAreaResponse> getAllByRecruitmentId(Long recruitmentId) {
        return queryFactory
            .selectFrom(recruitAreaEntity)
            .join(recruitAreaCodeEntity)
            .on(recruitAreaCodeEntity.recruitArea.id.eq(recruitAreaEntity.id))
            .join(recruitAreaCodeEntity.code, codeEntity)
            .where(recruitAreaEntity.recruitment.id.eq(recruitmentId))
            .transform(
                groupBy(recruitAreaEntity.id)
                    .list(
                        new QQueryRecruitAreaVO(
                            recruitAreaEntity.id,
                            recruitAreaEntity.hiredCount,
                            recruitAreaEntity.majorTask,
                            recruitAreaEntity.preferentialTreatment,
                            list(codeEntity)
                        )
                    )
            ).stream()
            .map(RecruitAreaResponse.class::cast)
            .toList();
    }
}
