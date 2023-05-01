package team.returm.jobis.domain.application.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.acceptance.domain.repository.vo.ApplicationDetailVO;
import team.returm.jobis.domain.acceptance.domain.repository.vo.QApplicationDetailVO;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.ApplicationAttachment;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.vo.QQueryApplicationVO;
import team.returm.jobis.domain.application.domain.repository.vo.QQueryFieldTraineesVO;
import team.returm.jobis.domain.application.domain.repository.vo.QueryApplicationVO;
import team.returm.jobis.domain.application.domain.repository.vo.QueryFieldTraineesVO;
import team.returm.jobis.domain.student.domain.Student;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static team.returm.jobis.domain.application.domain.QApplication.application;
import static team.returm.jobis.domain.application.domain.QApplicationAttachment.applicationAttachment;
import static team.returm.jobis.domain.company.domain.QCompany.company;
import static team.returm.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static team.returm.jobis.domain.student.domain.QStudent.student;

@RequiredArgsConstructor
@Repository
public class ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<QueryApplicationVO> queryApplicationByConditions(Long recruitmentId, Long studentId, ApplicationStatus applicationStatus,
                                                                 String studentName, Long page) {
        long pageSize = 11;
        return jpaQueryFactory
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
                .offset(page * pageSize)
                .limit(pageSize)
                .transform(
                        groupBy(application.id)
                                .list(
                                        new QQueryApplicationVO(
                                                application.id,
                                                student.name,
                                                student.grade,
                                                student.number,
                                                student.classRoom,
                                                company.name,
                                                list(applicationAttachment.attachmentUrl),
                                                application.createdAt,
                                                application.applicationStatus
                                        )
                                )
                );
    }

    public List<QueryFieldTraineesVO> queryApplicationsFieldTraineesByCompanyId(Long companyId) {
        return jpaQueryFactory
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

    public Application saveApplication(Application application) {
        return applicationJpaRepository.save(application);
    }

    public void deleteApplicationByIds(List<Long> applicationIds) {
        jpaQueryFactory
                .delete(application)
                .where(application.id.in(applicationIds))
                .execute();
    }

    public void saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments) {
        applicationAttachmentJpaRepository.saveAll(applicationAttachments);
    }

    public boolean existsApplicationByStudentAndRecruitmentId(Student student, Long recruitmentId) {
        return applicationJpaRepository.existsByStudentAndRecruitmentId(student, recruitmentId);
    }

    public boolean existsApplicationByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus) {
        return applicationJpaRepository.existsByStudentAndApplicationStatus(student, applicationStatus);
    }

    public List<Application> queryApplicationByIds(List<Long> applicationIds) {
        return applicationJpaRepository.findByIdIn(applicationIds);
    }

    public Integer queryApplicationCountByConditions(Long recruitmentId, Long studentId,
                                         ApplicationStatus applicationStatus, String studentName) {
        return jpaQueryFactory
                .select(application.id)
                .from(application)
                .join(application.student, student)
                .join(application.recruitment, recruitment)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .leftJoin(recruitment.company, company)
                .where(
                        eqRecruitmentId(recruitmentId),
                        eqStudentId(studentId),
                        eqApplicationStatus(applicationStatus),
                        containStudentName(studentName)
                ).distinct()
                .fetch()
                .size();
    }

    public List<ApplicationDetailVO> queryApplicationDetailsByIds(List<Long> applicationIds) {
        return jpaQueryFactory
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
        jpaQueryFactory
                .update(application)
                .set(application.applicationStatus, status)
                .where(application.in(applications))
                .execute();
    }

    public List<Application> queryApplicationsByStudentIds(List<Long> studentIds) {
        return applicationJpaRepository.findByStudentIdIn(studentIds);
    }

    public void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Application> applications) {
        jpaQueryFactory
                .update(application)
                .set(application.startDate, startDate)
                .set(application.endDate, endDate)
                .where(application.in(applications))
                .execute();
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
                JPAExpressions.select(recruitment.createdAt.max())
                        .from(recruitment)
                        .where(recruitment.company.id.eq(companyId))
        );
    }
}
