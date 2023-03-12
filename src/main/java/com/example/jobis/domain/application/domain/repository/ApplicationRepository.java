package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.ApplicationAttachment;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.vo.QQueryApplicationVO;
import com.example.jobis.domain.application.domain.repository.vo.QueryApplicationVO;
import com.example.jobis.domain.application.presentation.dto.request.QueryApplicationsRequest;
import com.example.jobis.domain.application.exception.ApplicationNotFoundException;
import com.example.jobis.domain.student.domain.Student;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.jobis.domain.application.domain.QApplication.application;
import static com.example.jobis.domain.application.domain.QApplicationAttachment.applicationAttachment;
import static com.example.jobis.domain.company.domain.QCompany.company;
import static com.example.jobis.domain.recruitment.domain.QRecruitment.recruitment;
import static com.example.jobis.domain.student.domain.QStudent.student;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<QueryApplicationVO> queryApplicationByConditions(QueryApplicationsRequest request) {
        return jpaQueryFactory
                .selectFrom(application)
                .join(application.student, student)
                .join(application.recruitment, recruitment)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .leftJoin(recruitment.company, company)
                .where(
                        eqRecruitmentId(request.getRecruitmentId()),
                        eqStudentId(request.getStudentId()),
                        eqApplicationStatus(request.getApplicationStatus()),
                        containStudentName(request.getStudentName())
                )
                .orderBy(application.createdAt.desc())
                .transform(
                        groupBy(application.id)
                                .list(
                                        new QQueryApplicationVO(
                                                application.id,
                                                student.name,
                                                student.grade,
                                                student.classRoom,
                                                student.number,
                                                company.name,
                                                list(applicationAttachment.attachmentUrl),
                                                application.createdAt,
                                                application.applicationStatus
                                        )
                                )
                );
    }

    public Application saveApplication(Application application) {
        return applicationJpaRepository.save(application);
    }

    public void saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments) {
        applicationAttachmentJpaRepository.saveAll(applicationAttachments);
    }

    public boolean existsApplicationByStudentAndRecruitmentId(Student student, UUID recruitmentId) {
        return applicationJpaRepository.existsByStudentAndRecruitmentId(student, recruitmentId);
    }

    public boolean existsApplicationByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus) {
        return applicationJpaRepository.existsByStudentAndApplicationStatus(student, applicationStatus);
    }

    public Optional<Application> queryApplicationById(UUID applicationId) {
        return applicationJpaRepository.findById(applicationId);
    }

    public void deleteApplication(Application application) {
        applicationJpaRepository.delete(application);
    }

    //==conditions==//

    private BooleanExpression eqRecruitmentId(UUID recruitmentId) {
        return recruitmentId == null ? null : recruitment.id.eq(recruitmentId);
    }

    private BooleanExpression eqStudentId(UUID studentId) {
        return studentId == null ? null : student.id.eq(studentId);
    }

    private BooleanExpression eqApplicationStatus(ApplicationStatus status) {
        return status == null ? null : application.applicationStatus.eq(status);
    }

    private BooleanExpression containStudentName(String studentName) {
        return studentName == null ? null : student.name.contains(studentName);
    }
}
