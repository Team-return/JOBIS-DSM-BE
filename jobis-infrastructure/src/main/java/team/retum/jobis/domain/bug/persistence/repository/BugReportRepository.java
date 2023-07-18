package team.retum.jobis.domain.bug.persistence.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.bug.persistence.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.BugReportEntity;
import com.example.jobisapplication.domain.bug.domain.DevelopmentArea;
import team.retum.jobis.domain.bug.persistence.repository.vo.QQueryBugReportsVO;
import team.retum.jobis.domain.bug.persistence.repository.vo.QueryBugReportsVO;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static team.retum.jobis.domain.bug.persistence.QBugAttachment.bugAttachment;
import static team.retum.jobis.domain.bug.persistence.QBugReport.bugReport;

@RequiredArgsConstructor
@Repository
public class BugReportRepository {

    private final BugReportJpaRepository bugReportJpaRepository;
    private final BugAttachmentRepository bugAttachmentRepository;
    private final JPAQueryFactory queryFactory;

    public BugReportEntity saveBugReport(BugReportEntity bugReportEntity) {
        return bugReportJpaRepository.save(bugReportEntity);
    }

    public void saveAllBugAttachment(List<BugAttachmentEntity> bugAttachmentEntity) {
        bugAttachmentRepository.saveAll(bugAttachmentEntity);
    }

    public Optional<BugReportEntity> queryBugReportById(Long id) {
        return bugReportJpaRepository.findById(id);
    }

    public List<QueryBugReportsVO> queryBugReportsByConditions(DevelopmentArea developmentArea) {
        return queryFactory
                .selectFrom(bugReport)
                .leftJoin(bugReport.bugAttachments, bugAttachment)
                .where(eqDevelopmentArea(developmentArea))
                .orderBy(bugReport.createdAt.desc())
                .transform(
                        groupBy(bugReport.id)
                                .list(
                                        new QQueryBugReportsVO(
                                                bugReport.id,
                                                bugReport.title,
                                                bugReport.developmentArea,
                                                bugReport.createdAt
                                        )
                                )
                );
    }

    //==conditions==//

    private BooleanExpression eqDevelopmentArea(DevelopmentArea developmentArea) {
        return developmentArea == null || developmentArea == DevelopmentArea.ALL
                ? null : bugReport.developmentArea.eq(developmentArea);
    }
}
