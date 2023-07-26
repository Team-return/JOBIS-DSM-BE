package team.retum.jobis.domain.application.persistence;

import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.model.ApplicationAttachment;
import com.example.jobisapplication.domain.application.spi.ApplicationPort;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.acceptance.persistence.repository.vo.QApplicationDetailVO;
import team.retum.jobis.domain.acceptance.persistence.repository.vo.QueryApplicationDetailVO;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.mapper.ApplicationAttachmentMapper;
import team.retum.jobis.domain.application.persistence.mapper.ApplicationMapper;
import team.retum.jobis.domain.application.persistence.repository.ApplicationAttachmentJpaRepository;
import team.retum.jobis.domain.application.persistence.repository.ApplicationJpaRepository;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryApplicationVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryFieldTraineesVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryPassedApplicationStudentsVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QQueryTotalApplicationCountVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryApplicationVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryFieldTraineesVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryPassedApplicationStudentsVO;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryTotalApplicationCountVO;
import team.retum.jobis.domain.student.persistence.QStudent;

import java.time.LocalDate;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.jpa.JPAExpressions.select;
import static team.retum.jobis.domain.application.persistence.QApplication.application;
import static team.retum.jobis.domain.application.persistence.QApplicationAttachment.applicationAttachment;
import static team.retum.jobis.domain.company.persistence.QCompany.company;
import static team.retum.jobis.domain.recruitment.persistence.QRecruitment.recruitment;
import static team.retum.jobis.domain.student.persistence.QStudent.student;

@RequiredArgsConstructor
@Repository
public class ApplicationPersistenceAdapter implements ApplicationPort {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;
    private final ApplicationMapper applicationMapper;
    private final ApplicationAttachmentMapper applicationAttachmentMapper;
    private final JPAQueryFactory queryFactory;

    @Override
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

    @Override
    public Long queryApplicationCountByCondition(ApplicationStatus applicationStatus, String studentName) {
        return queryFactory
                .select(application.count())
                .from(application)
                .where(
                        eqApplicationStatus(applicationStatus),
                        containStudentName(studentName)
                ).fetchOne();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Application saveApplication(Application application) {
        return applicationMapper.toDomain(
                applicationJpaRepository.save(applicationMapper.toEntity(application))
        );
    }

    @Override
    public void deleteApplicationByIds(List<Long> applicationIds) {
        applicationJpaRepository.deleteByIdIn(applicationIds);
    }

    @Override
    public void saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments) {
        applicationAttachmentJpaRepository.saveAll(
                applicationAttachments.stream()
                        .map(applicationAttachmentMapper::toEntity)
                        .toList()
        );
    }

    @Override
    public List<Application> queryApplicationsByIds(List<Long> applicationIds) {
        return applicationJpaRepository.findAllByIdIn(applicationIds).stream()
                .map(applicationMapper::toDomain)
                .toList();
    }

    @Override
    public List<QueryApplicationDetailVO> queryApplicationDetailsByIds(List<Long> applicationIds) {
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

    @Override
    public Application queryApplicationById(Long applicationId) {
        return applicationMapper.toDomain(
                applicationJpaRepository.findById(applicationId)
                        .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION)
        );
    }

    @Override
    public void deleteApplication(Application application) {
        applicationJpaRepository.delete(
                applicationMapper.toEntity(application)
        );
    }

    @Override
    public void changeApplicationStatus(ApplicationStatus status, List<Long> applicationIds) {
        queryFactory
                .update(application)
                .set(application.applicationStatus, status)
                .where(application.id.in(applicationIds))
                .execute();
    }

    @Override
    public void updateFieldTrainDate(LocalDate startDate, LocalDate endDate, List<Long> applicationIds) {
        queryFactory
                .update(application)
                .set(application.startDate, startDate)
                .set(application.endDate, endDate)
                .where(application.id.in(applicationIds))
                .execute();
    }

    @Override
    public boolean existsApplicationByStudentIdAndApplicationStatusIn(
            Long studentId,
            List<ApplicationStatus> applicationStatuses
    ) {
        return applicationJpaRepository.existsByStudentIdAndApplicationStatusIn(studentId, applicationStatuses);
    }

    @Override
    public void saveAllApplications(List<Application> applications) {
        applicationJpaRepository.saveAll(
                applications.stream()
                        .map(applicationMapper::toEntity)
                        .toList()
        );
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
