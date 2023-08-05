package team.retum.jobis.domain.bug.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.persistence.entity.BugReportEntity;

@RequiredArgsConstructor
@Component
public class BugReportMapper {

    public BugReportEntity toEntity(BugReport domain) {
        return BugReportEntity.builder()
                .id(domain.getId())
                .content(domain.getContent())
                .title(domain.getTitle())
                .developmentArea(domain.getDevelopmentArea())
                .build();
    }

    public BugReport toDomain(BugReportEntity entity) {
        return BugReport.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .title(entity.getTitle())
                .developmentArea(entity.getDevelopmentArea())
                .build();
    }
}
