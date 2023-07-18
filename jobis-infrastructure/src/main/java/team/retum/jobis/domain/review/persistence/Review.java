package team.retum.jobis.domain.review.persistence;

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
public class Review {

    @Id
    private String id;
    private Long companyId;
    private List<QnAElement> qnAElements;
    private String studentName;
    private Integer year;
    private LocalDate createdDate;

    @Builder
    public Review(Long companyId, List<QnAElement> qnAElements,
                  String studentName, Integer year) {
        this.companyId = companyId;
        this.qnAElements = qnAElements;
        this.studentName = studentName;
        this.year = year;
        this.createdDate = LocalDate.now();
    }
}
