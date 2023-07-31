package team.retum.jobis.domain.application.persistence.mapper;

import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.application.persistence.entity.ApplicationAttachmentEntity;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.repository.ApplicationJpaRepository;

@RequiredArgsConstructor
@Component
public class ApplicationAttachmentMapper {

    private final ApplicationJpaRepository applicationJpaRepository;

    public ApplicationAttachmentEntity toEntity(ApplicationAttachment domain) {
        ApplicationEntity application = applicationJpaRepository.findById(domain.getApplicationId())
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        return ApplicationAttachmentEntity.builder()
                .attachmentUrl(domain.getAttachmentUrl())
                .type(domain.getType())
                .applicationEntity(application)
                .build();
    }

    public ApplicationAttachment toDomain(ApplicationAttachmentEntity entity) {
        return ApplicationAttachment.builder()
                .attachmentUrl(entity.getAttachmentUrl())
                .type(entity.getType())
                .applicationId(entity.getApplication().getId())
                .build();
    }
}
