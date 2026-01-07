package team.retum.jobis.domain.notice.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.notice.model.ViewLog;
import team.retum.jobis.domain.notice.persistence.mapper.ViewLogMapper;
import team.retum.jobis.domain.notice.persistence.repository.ViewLogJpaRepository;
import team.retum.jobis.domain.notice.persistence.repository.vo.QQueryViewerVO;
import team.retum.jobis.domain.notice.spi.CommandViewLogPort;
import team.retum.jobis.domain.notice.spi.QueryViewLogPort;
import team.retum.jobis.domain.notice.spi.vo.ViewerVO;

import java.util.List;

import static team.retum.jobis.domain.notice.persistence.entity.QViewLogEntity.viewLogEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@RequiredArgsConstructor
@Repository
public class ViewLogPersistenceAdapter implements CommandViewLogPort, QueryViewLogPort {

    private final ViewLogJpaRepository viewLogJpaRepository;
    private final ViewLogMapper viewLogMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public ViewLog save(ViewLog viewLog) {
        return viewLogMapper.toDomain(viewLogJpaRepository.save(viewLogMapper.toEntity(viewLog)));
    }

    @Override
    public boolean existsByNoticeIdAndStudentId(Long noticeId, Long studentId) {
        return queryFactory
            .selectOne()
            .from(viewLogEntity)
            .where(
                eqNoticeId(noticeId),
                eqStudentId(studentId)
            )
            .fetchFirst() != null;
    }

    @Override
    public Long countByNoticeId(Long noticeId) {
        Long count = queryFactory
            .select(viewLogEntity.count())
            .from(viewLogEntity)
            .where(eqNoticeId(noticeId))
            .fetchOne();

        return count != null ? count : 0L;
    }

    @Override
    public List<ViewerVO> getViewersByNoticeId(Long noticeId) {
        return queryFactory
            .select(
                new QQueryViewerVO(
                    studentEntity.id,
                    studentEntity.name,
                    studentEntity.grade,
                    studentEntity.classRoom,
                    studentEntity.number,
                    viewLogEntity.viewedAt
                )
            )
            .from(viewLogEntity)
            .join(viewLogEntity.student, studentEntity)
            .where(eqNoticeId(noticeId))
            .orderBy(viewLogEntity.viewedAt.desc())
            .fetch()
            .stream()
            .map(ViewerVO.class::cast)
            .toList();
    }

    //==conditions==//

    private BooleanExpression eqNoticeId(Long noticeId) {
        return noticeId == null ? null : viewLogEntity.notice.id.eq(noticeId);
    }

    private BooleanExpression eqStudentId(Long studentId) {
        return studentId == null ? null : viewLogEntity.student.id.eq(studentId);
    }
}
