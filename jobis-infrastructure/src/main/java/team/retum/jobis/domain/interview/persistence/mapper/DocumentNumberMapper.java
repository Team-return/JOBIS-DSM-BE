package team.retum.jobis.domain.interview.persistence.mapper;

import org.springframework.stereotype.Component;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.persistence.entity.DocumentNumberEntity;

@Component
public class DocumentNumberMapper {

    public DocumentNumberEntity toEntity(DocumentNumber domain) {
        return DocumentNumberEntity.builder()
            .id(domain.getId())
            .documentNumber(domain.getDocumentNumber())
            .build();
    }

    public DocumentNumber toDomain(DocumentNumberEntity entity) {
        return DocumentNumber.builder()
            .id(entity.getId())
            .documentNumber(entity.getDocumentNumber())
            .build();
    }
}
