package team.retum.jobis.domain.acceptance.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.domain.enums.ApplicationStatus;
import team.retum.jobis.domain.company.domain.Company;

@Getter
public class ApplicationDetailVO {

    private final Long id;

    private final String studentName;

    private final int studentGrade;

    private final int studentClassNumber;

    private final int studentNumber;

    private final Company company;

    private final ApplicationStatus status;

    @QueryProjection
    public ApplicationDetailVO(Long id, String studentName, int studentGrade, int studentClassNumber,
                               int studentNumber, Company company, ApplicationStatus status) {
        this.id = id;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
        this.studentClassNumber = studentClassNumber;
        this.studentNumber = studentNumber;
        this.company = company;
        this.status = status;
    }
}
