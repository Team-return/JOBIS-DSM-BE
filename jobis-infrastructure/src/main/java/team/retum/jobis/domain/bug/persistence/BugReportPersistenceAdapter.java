package team.retum.jobis.domain.bug.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.persistence.mapper.BugReportMapper;
import team.retum.jobis.domain.bug.persistence.repository.BugReportJpaRepository;
import team.retum.jobis.domain.bug.persistence.repository.vo.QQueryBugReportsVO;
import team.retum.jobis.domain.bug.spi.BugReportPort;
import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static team.retum.jobis.domain.bug.persistence.entity.QBugAttachmentEntity.bugAttachmentEntity;
import static team.retum.jobis.domain.bug.persistence.entity.QBugReportEntity.bugReportEntity;

@RequiredArgsConstructor
@Repository
public class BugReportPersistenceAdapter implements BugReportPort {

    private final BugReportJpaRepository bugReportJpaRepository;
    private final BugReportMapper bugReportMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public BugReport saveBugReport(BugReport bugReport) {
        return bugReportMapper.toDomain(
            bugReportJpaRepository.save(
                bugReportMapper.toEntity(bugReport)
            )
        );
    }

    @Override
    public Optional<BugReport> queryBugReportById(Long id) {
        return bugReportJpaRepository.findById(id)
            .map(bugReportMapper::toDomain);
    }

    @Override
    public List<BugReportsVO> queryBugReportsByDevelopmentArea(DevelopmentArea developmentArea) {
        return queryFactory
            .selectFrom(bugReportEntity)
            .leftJoin(bugReportEntity.attachments, bugAttachmentEntity)
            .where(eqDevelopmentArea(developmentArea))
            .orderBy(bugReportEntity.createdAt.desc())
            .transform(
                groupBy(bugReportEntity.id)
                    .list(
                        new QQueryBugReportsVO(
                            bugReportEntity.id,
                            bugReportEntity.title,
                            bugReportEntity.developmentArea,
                            bugReportEntity.createdAt
                        )
                    )
            ).stream()
            .map(BugReportsVO.class::cast)
            .toList();
    }

    //==conditions==//

    private BooleanExpression eqDevelopmentArea(DevelopmentArea developmentArea) {
        return developmentArea == null || developmentArea == DevelopmentArea.ALL
            ? null : bugReportEntity.developmentArea.eq(developmentArea);
    }
}
