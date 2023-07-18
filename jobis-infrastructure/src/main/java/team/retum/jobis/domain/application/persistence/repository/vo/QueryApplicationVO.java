package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.persistence.ApplicationAttachmentEntity;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QueryApplicationVO {

    private final Long id;
    private final String name;
    private final Integer grade;
    private final Integer number;
    private final Integer classNumber;
    private final String profileImageUrl;
    private final String companyName;
    private final List<ApplicationAttachmentEntity> applicationAttachmentEntities;
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;

    @QueryProjection
    public QueryApplicationVO(Long id, String name, Integer grade, Integer number,
                              Integer classNumber, String profileImageUrl, String companyName,
                              List<ApplicationAttachmentEntity> applicationAttachmentEntities, LocalDateTime createdAt,
                              ApplicationStatus applicationStatus) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.classNumber = classNumber;
        this.profileImageUrl = profileImageUrl;
        this.companyName = companyName;
        this.applicationAttachmentEntities = applicationAttachmentEntities;
        this.createdAt = createdAt;
        this.applicationStatus = applicationStatus;
    }
}
