package team.retum.jobis.domain.acceptance.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;

@Getter
public class ApplicationDetailVO {

    private final Long id;

    private final String studentName;

    private final int studentGrade;

    private final int studentClassNumber;

    private final int studentNumber;

    private final CompanyEntity companyEntity;

    private final ApplicationStatus status;

    @QueryProjection
    public ApplicationDetailVO(Long id, String studentName, int studentGrade, int studentClassNumber,
                               int studentNumber, CompanyEntity companyEntity, ApplicationStatus status) {
        this.id = id;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
        this.studentClassNumber = studentClassNumber;
        this.studentNumber = studentNumber;
        this.companyEntity = companyEntity;
        this.status = status;
    }
}
