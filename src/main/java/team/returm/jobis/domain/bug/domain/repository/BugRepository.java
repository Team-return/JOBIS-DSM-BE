package team.returm.jobis.domain.bug.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.bug.domain.BugAttachment;
import team.returm.jobis.domain.bug.domain.BugReport;

import java.util.List;

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
}
