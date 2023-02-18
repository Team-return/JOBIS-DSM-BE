package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.controller.dto.response.ApplicationDetailsResponse;
import com.example.jobis.domain.application.controller.dto.response.QApplicationDetailsResponse;
import com.example.jobis.domain.application.controller.dto.response.QStudentApplicationListResponse;
import com.example.jobis.domain.application.controller.dto.response.StudentApplicationListResponse;
import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.ApplicationAttachment;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.exception.ApplicationNotFoundException;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.student.domain.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.jobis.domain.application.domain.QApplication.application;
import static com.example.jobis.domain.application.domain.QApplicationAttachment.applicationAttachment;
import static com.example.jobis.domain.company.domain.QCompany.company;
import static com.example.jobis.domain.recruit.domain.QRecruitment.recruitment;
import static com.example.jobis.domain.student.domain.QStudent.student;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.set;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<StudentApplicationListResponse> queryStudentApplication(Student student) {
        return jpaQueryFactory
                .select(
                        new QStudentApplicationListResponse(
                                application.id,
                                application.recruitment.company.name,
                                application.applicationStatus,
                                application.createdAt
                        )
                )
                .from(application)
                .leftJoin(application.recruitment)
                .where(application.student.eq(student))
                .orderBy(application.createdAt.desc())
                .fetch();
    }

    public ApplicationDetailsResponse queryApplicationDetails(UUID applicationId) {
        return jpaQueryFactory
                .selectFrom(application)
                .leftJoin(application.student, student)
                .leftJoin(application.recruitment, recruitment)
                .leftJoin(recruitment.company, company)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .where(application.id.eq(applicationId))
                .transform(
                        groupBy(application.id)
                                .as(
                                        new QApplicationDetailsResponse(
                                                student.name,
                                                company.name,
                                                set(applicationAttachment.attachmentUrl),
                                                application.applicationStatus
                                        )
                                )
                ).get(applicationId);
    }

    public Application saveApplication(Application application) {
        return applicationJpaRepository.save(application);
    }

    public List<ApplicationAttachment> saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments) {
        return applicationAttachmentJpaRepository.saveAll(applicationAttachments);
    }

    public boolean existsApplicationByStudentAndCompany(Student student, Recruitment recruitment) {
        return applicationJpaRepository.existsByStudentAndRecruitment(student, recruitment);
    }

    public boolean existsApplicationByStudentAndApplicationStatus(Student student, ApplicationStatus applicationStatus) {
        return applicationJpaRepository.existsByStudentAndApplicationStatus(student, applicationStatus);
    }

    public Application findApplicationById(UUID applicationId) {
        return applicationJpaRepository.findById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
    }

    public void deleteApplication(Application application) {
        applicationJpaRepository.delete(application);
    }
}
