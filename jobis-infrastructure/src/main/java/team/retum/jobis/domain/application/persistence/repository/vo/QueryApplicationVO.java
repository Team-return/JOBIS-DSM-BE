package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.entity.ApplicationAttachmentEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QueryApplicationVO {

    private final Long id;
    private final Long recruitmentId;
    private final String name;
    private final Integer grade;
    private final Integer number;
    private final Integer classNumber;
    private final String profileImageUrl;
    private final String companyName;
    private final String companyLogoUrl;
    private final List<ApplicationAttachmentEntity> applicationAttachmentEntities;
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;


    @QueryProjection
    public QueryApplicationVO(Long id, Long recruitmentId, String name, Integer grade, Integer number,
                              Integer classNumber, String profileImageUrl, String companyName, String companyLogoUrl,
                              List<ApplicationAttachmentEntity> applicationAttachmentEntities,
                              LocalDateTime createdAt, ApplicationStatus applicationStatus) {
        this.id = id;
        this.recruitmentId = recruitmentId;
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.classNumber = classNumber;
        this.profileImageUrl = profileImageUrl;
        this.companyName = companyName;
        this.companyLogoUrl = companyLogoUrl;
        this.applicationAttachmentEntities = applicationAttachmentEntities;
        this.createdAt = createdAt;
        this.applicationStatus = applicationStatus;
    }
}
