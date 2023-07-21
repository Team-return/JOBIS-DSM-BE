package team.retum.jobis.domain.bug.persistence;

import com.example.jobisapplication.domain.bug.exception.BugReportNotFoundException;
import com.example.jobisapplication.domain.bug.model.BugAttachment;
import com.example.jobisapplication.domain.bug.model.BugReport;
import com.example.jobisapplication.domain.bug.spi.BugReportPort;
import com.example.jobisapplication.domain.bug.spi.vo.BugReportsVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.persistence.mapper.BugAttachmentMapper;
import team.retum.jobis.domain.bug.persistence.mapper.BugReportMapper;
import team.retum.jobis.domain.bug.persistence.repository.BugAttachmentJpaRepository;
import team.retum.jobis.domain.bug.persistence.repository.BugReportJpaRepository;
import team.retum.jobis.domain.bug.persistence.repository.vo.QQueryBugReportsVO;
import team.retum.jobis.domain.bug.persistence.repository.vo.QueryBugReportsVO;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static team.retum.jobis.domain.bug.persistence.entity.QBugAttachment.bugAttachment;
import static team.retum.jobis.domain.bug.persistence.entity.QBugReport.bugReport;

@RequiredArgsConstructor
@Repository
public class BugReportPersistenceAdapter implements BugReportPort {

    private final BugReportJpaRepository bugReportJpaRepository;
    private final BugReportMapper bugReportMapper;
    private final BugAttachmentJpaRepository bugAttachmentJpaRepository;
    private final BugAttachmentMapper bugAttachmentMapper;
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
    public List<BugAttachment> saveAllBugAttachment(List<BugAttachment> bugAttachments) {
        return bugAttachmentJpaRepository.saveAll(
                bugAttachments.stream()
                        .map(bugAttachmentMapper::toEntity)
                        .toList()
                ).stream()
                .map(bugAttachmentMapper::toDomain)
                .toList();
    }

    @Override
    public BugReport queryBugReportById(Long id) {
        return bugReportMapper.toDomain(
                bugReportJpaRepository.findById(id)
                        .orElseThrow(() -> BugReportNotFoundException.EXCEPTION)
        );
    }

    @Override
    public List<BugReportsVO> queryBugReportsByDevelopmentArea(DevelopmentArea developmentArea) {
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
