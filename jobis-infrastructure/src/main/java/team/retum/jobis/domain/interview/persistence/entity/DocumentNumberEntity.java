package team.retum.jobis.domain.interview.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tbl_document_number")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DocumentNumberEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String documentNumber;

    @Builder
    public DocumentNumberEntity(Long id, String documentNumber) {
        this.id = id;
        this.documentNumber = documentNumber;
    }
}
