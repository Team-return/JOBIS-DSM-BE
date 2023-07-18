package team.retum.jobis.domain.review.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document("reviews")
public class ReviewEntity {

    @Id
    private String id;
    private Long companyId;
    private List<QnAElementEntity> qnAElementEntities;
    private String studentName;
    private Integer year;
    private LocalDate createdDate;

    @Builder
    public ReviewEntity(Long companyId, List<QnAElementEntity> qnAElementEntities,
                        String studentName, Integer year) {
        this.companyId = companyId;
        this.qnAElementEntities = qnAElementEntities;
        this.studentName = studentName;
        this.year = year;
        this.createdDate = LocalDate.now();
    }
}
