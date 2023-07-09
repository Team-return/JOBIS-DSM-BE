package team.retum.jobis.domain.bug.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.bug.domain.BugAttachment;
import team.retum.jobis.domain.bug.domain.BugReport;
import team.retum.jobis.domain.bug.domain.repository.vo.QQueryBugReportVO;
import team.retum.jobis.domain.bug.domain.repository.vo.QueryBugReportVO;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static team.retum.jobis.domain.bug.domain.QBugAttachment.bugAttachment;
import static team.retum.jobis.domain.bug.domain.QBugReport.bugReport;

@RequiredArgsConstructor
@Repository
public class BugRepository {

    private final BugReportJpaRepository bugReportJpaRepository;
    private final BugAttachmentRepository bugAttachmentRepository;
    private final JPAQueryFactory queryFactory;

    public BugReport saveBugReport(BugReport bugReport) {
        return bugReportJpaRepository.save(bugReport);
    }

    public void saveAllBugAttachment(List<BugAttachment> bugAttachment) {
        bugAttachmentRepository.saveAll(bugAttachment);
    }

    public List<QueryBugReportVO> queryBugReports() {
        return queryFactory
                .selectFrom(bugReport)
                .leftJoin(bugReport.bugAttachments, bugAttachment)
                .orderBy(bugReport.createdAt.desc())
                .transform(
                        groupBy(bugReport.id)
                                .list(
                                        new QQueryBugReportVO(
                                                bugReport.title,
                                                bugReport.content,
                                                bugReport.developmentArea,
                                                list(bugAttachment.attachmentUrl)
                                        )
                                )
                );
    }
}
