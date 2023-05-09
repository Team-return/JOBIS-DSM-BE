package team.returm.jobis.domain.review.domain;

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
    private String companyName;
    private String studentName;
    private String studentGcn;
    private Integer year;
    private LocalDate createdDate;

    @Builder
    public Review(Long companyId, String companyName, List<QnAElement> qnAElements,
                  String studentName, String studentGcn, Integer year) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.qnAElements = qnAElements;
        this.studentName =studentName;
        this.studentGcn = studentGcn;
        this.year = year;
        this.createdDate = LocalDate.now();
    }
}
