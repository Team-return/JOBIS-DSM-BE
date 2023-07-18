package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bug.persistence.entity.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.entity.BugReportEntity;
import team.retum.jobis.domain.bug.persistence.repository.BugReportRepository;
import team.retum.jobis.domain.bug.exception.BugReportNotFoundException;
import team.retum.jobis.domain.bug.presentation.dto.response.QueryBugReportDetailsResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryBugReportDetailsService {

    private final BugReportRepository bugReportRepository;

    public QueryBugReportDetailsResponse execute(Long bugReportId) {
        BugReportEntity bugReportEntity = bugReportRepository.queryBugReportById(bugReportId)
                .orElseThrow(() -> BugReportNotFoundException.EXCEPTION);

        return QueryBugReportDetailsResponse.builder()
                .title(bugReportEntity.getTitle())
                .content(bugReportEntity.getContent())
                .developmentArea(bugReportEntity.getDevelopmentArea())
                .attachments(
                        bugReportEntity.getBugAttachmentEntities().stream()
                                .map(BugAttachmentEntity::getAttachmentUrl)
                                .toList())
                .createdAt(bugReportEntity.getCreatedAt())
                .build();
    }
}
