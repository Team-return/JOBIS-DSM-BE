package team.retum.jobis.domain.application.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.acceptance.domain.repository.vo.ApplicationDetailVO;
import team.retum.jobis.domain.acceptance.domain.repository.vo.QApplicationDetailVO;
import team.retum.jobis.domain.application.domain.Application;
import team.retum.jobis.domain.application.domain.ApplicationAttachment;
import team.retum.jobis.domain.application.domain.enums.ApplicationStatus;
import team.retum.jobis.domain.application.domain.repository.vo.QQueryApplicationVO;
import team.retum.jobis.domain.application.domain.repository.vo.QQueryFieldTraineesVO;
import team.retum.jobis.domain.application.domain.repository.vo.QQueryPassedApplicationStudentsVO;
import team.retum.jobis.domain.application.domain.repository.vo.QQueryTotalApplicationCountVO;
import team.retum.jobis.domain.application.domain.repository.vo.QueryApplicationVO;
import team.retum.jobis.domain.application.domain.repository.vo.QueryFieldTraineesVO;
import team.retum.jobis.domain.application.domain.repository.vo.QueryPassedApplicationStudentsVO;
import team.retum.jobis.domain.application.domain.repository.vo.QueryTotalApplicationCountVO;
import team.retum.jobis.domain.student.domain.QStudent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.jpa.JPAExpressions.select;
import static team.retum.jobis.domain.application.domain.QApplication.application;
import static team.retum.jobis.domain.application.domain.QApplicationAttachment.applicationAttachment;
import static team.retum.jobis.domain.company.domain.QCompany.company;
import static team.retum.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static team.retum.jobis.domain.student.domain.QStudent.student;

@RequiredArgsConstructor
@Repository
public class ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;
    private final JPAQueryFactory queryFactory;

    public List<QueryApplicationVO> queryApplicationByConditions(Long recruitmentId, Long studentId, ApplicationStatus applicationStatus, String studentName) {
        return queryFactory
                .selectFrom(application)
                .join(application.student, student)
                .join(application.recruitment, recruitment)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .leftJoin(recruitment.company, company)
                .where(
                        eqRecruitmentId(recruitmentId),
                        eqStudentId(studentId),
                        eqApplicationStatus(applicationStatus),
                        containStudentName(studentName)
                )
                .orderBy(application.createdAt.desc())
                .transform(
                        groupBy(application.id)
                                .list(
                                        new QQueryApplicationVO(
                                                application.id,
                                                student.name,
                                                student.grade,
                                                student.number,
                                                student.classRoom,
                                                student.profileImageUrl,
                                                company.name,
                                                list(applicationAttachment),
                                                application.createdAt,
                                                application.applicationStatus
                                        )
                                )
                );
    }

    public Long getApplicationCountByCondition(ApplicationStatus applicationStatus, String studentName) {
        return queryFactory
                .select(application.count())
                .from(application)
                .where(
                        eqApplicationStatus(applicationStatus),
                        containStudentName(studentName)
                ).fetchOne();
    }

    public List<QueryFieldTraineesVO> queryApplicationsFieldTraineesByCompanyId(Long companyId) {
        return queryFactory
                .select(
                        new QQueryFieldTraineesVO(
                                application.id,
                                student.grade,
                                student.classRoom,
                                student.number,
                                student.name,
                                application.startDate,
                                application.endDate
                        )
                )
                .from(application)
                .join(application.student, student)
                .on(application.student.id.eq(student.id))
                .join(application.recruitment, recruitment)
                .on(recentRecruitment(companyId))
                .where(application.applicationStatus.eq(ApplicationStatus.FIELD_TRAIN))
                .fetch();
    }

    public List<QueryPassedApplicationStudentsVO> queryPassedApplicationStudentsByCompanyId(Long companyId) {
        return queryFactory
                .select(
                        new QQueryPassedApplicationStudentsVO(
                                application.id,
                                student.name,
                                student.grade,
                                student.classRoom,
                                student.number
                        )
                )
                .from(application)
                .join(application.student, student)
                .join(application.recruitment, recruitment)
                .join(recruitment.company, company)
                .where(
                        company.id.eq(companyId),
                        application.applicationStatus.eq(ApplicationStatus.PASS)
                )
                .fetch();
    }

    public QueryTotalApplicationCountVO queryTotalApplicationCount() {
        QStudent approvedStudent = new QStudent("approvedStudent");
        QStudent passedStudent = new QStudent("passedStudent");
        return queryFactory
                .select(
                        new QQueryTotalApplicationCountVO(
                                student.count(),
                                passedStudent.countDistinct(),
                                approvedStudent.countDistinct()
                        )
                )
                .from(application)
                .leftJoin(application.student, approvedStudent)
                .on(approvedStudent.applications.any().applicationStatus.eq(ApplicationStatus.APPROVED))
                .leftJoin(application.student, passedStudent)
                .on(
                        passedStudent.applications.any().applicationStatus.eq(ApplicationStatus.PASS)
                                .or(passedStudent.applications.any().applicationStatus.eq(ApplicationStatus.FIELD_TRAIN))
                )
                .rightJoin(application.student, student)
                .fetchOne();
    }

    public Application saveApplication(Application application) {
        return applicationJpaRepository.save(application);
    }

    public void deleteApplicationByIds(List<Long> applicationIds) {
        applicationJpaRepository.deleteByIdIn(applicationIds);
    }

    public void saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments) {
        applicationAttachmentJpaRepository.saveAll(applicationAttachments);
    }

    public List<Application> queryApplicationsByIds(List<Long> applicationIds) {
        return applicationJpaRepository.findAllByIdIn(applicationIds);
    }

    public List<ApplicationDetailVO> queryApplicationDetailsByIds(List<Long> applicationIds) {
        return queryFactory
                .select(
                        new QApplicationDetailVO(
                                application.id,
                                student.name,
                                student.grade,
                                student.classRoom,
                                student.number,
                                company,
                                application.applicationStatus
                        )
                )
                .from(application)
                .join(application.student, student)
                .join(application.recruitment, recruitment)
                .join(recruitment.company, company)
                .where(application.id.in(applicationIds))
                .fetch();
    }

    public Optional<Application> queryApplicationById(Long applicationId) {
        return applicationJpaRepository.findById(applicationId);
    }

    public void deleteApplication(Application application) {
        applicationJpaRepository.delete(application);
    }

    public void changeApplicationStatus(ApplicationStatus status, List<Application> applications) {
        queryFactory
                .update(application)
                .set(application.applicationStatus, status)
                .where(application.in(applications))
                .execute();
    }

    public void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Application> applications) {
        queryFactory
                .update(application)
                .set(application.startDate, startDate)
                .set(application.endDate, endDate)
                .where(application.in(applications))
                .execute();
    }

    public boolean existsApplicationByStudentIdAndApplicationStatusIn(
            Long studentId,
            List<ApplicationStatus> applicationStatuses
    ) {
        return applicationJpaRepository.existsByStudentIdAndApplicationStatusIn(studentId, applicationStatuses);
    }

    public void saveAllApplications(List<Application> applications) {
        applicationJpaRepository.saveAll(applications);
    }

    //==conditions==//

    private BooleanExpression eqRecruitmentId(Long recruitmentId) {
        return recruitmentId == null ? null : recruitment.id.eq(recruitmentId);
    }

    private BooleanExpression eqStudentId(Long studentId) {
        return studentId == null ? null : student.id.eq(studentId);
    }

    private BooleanExpression eqApplicationStatus(ApplicationStatus status) {
        return status == null ? null : application.applicationStatus.eq(status);
    }

    private BooleanExpression containStudentName(String studentName) {
        return studentName == null ? null : student.name.contains(studentName);
    }

    private BooleanExpression recentRecruitment(Long companyId) {
        return recruitment.createdAt.eq(
                select(recruitment.createdAt.max())
                        .from(recruitment)
                        .where(recruitment.company.id.eq(companyId))
        );
    }
}
