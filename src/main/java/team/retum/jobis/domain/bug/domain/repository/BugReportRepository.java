package team.retum.jobis.domain.bug.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.bug.domain.BugAttachment;
import team.retum.jobis.domain.bug.domain.BugReport;
import team.retum.jobis.domain.bug.domain.enums.DevelopmentArea;
import team.retum.jobis.domain.bug.domain.repository.vo.QQueryBugReportsVO;
import team.retum.jobis.domain.bug.domain.repository.vo.QueryBugReportsVO;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static team.retum.jobis.domain.bug.domain.QBugAttachment.bugAttachment;
import static team.retum.jobis.domain.bug.domain.QBugReport.bugReport;

@RequiredArgsConstructor
@Repository
public class BugReportRepository {

    private final BugReportJpaRepository bugReportJpaRepository;
    private final BugAttachmentRepository bugAttachmentRepository;
    private final JPAQueryFactory queryFactory;

    public BugReport saveBugReport(BugReport bugReport) {
        return bugReportJpaRepository.save(bugReport);
    }

    public void saveAllBugAttachment(List<BugAttachment> bugAttachment) {
        bugAttachmentRepository.saveAll(bugAttachment);
    }

    public Optional<BugReport> queryBugReportById(Long id) {
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
