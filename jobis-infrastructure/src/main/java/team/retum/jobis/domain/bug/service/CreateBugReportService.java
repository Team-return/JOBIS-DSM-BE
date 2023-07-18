package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bug.persistence.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.BugReportEntity;
import team.retum.jobis.domain.bug.persistence.repository.BugReportRepository;
import team.retum.jobis.domain.bug.presentation.dto.request.CreateBugReportRequest;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class CreateBugReportService {

    private final BugReportRepository bugReportRepository;

    public void execute(CreateBugReportRequest request) {
        BugReportEntity bugReportEntity = bugReportRepository.saveBugReport(
                BugReportEntity.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .developmentArea(request.getDevelopmentArea())
                        .build()
        );

        bugReportRepository.saveAllBugAttachment(
                request.getAttachmentUrls().stream()
                        .map(attachmentUrl ->
                                new BugAttachmentEntity(bugReportEntity, attachmentUrl)
                        ).toList()
        );
    }
}
