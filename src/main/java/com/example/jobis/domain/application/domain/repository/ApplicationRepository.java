package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.controller.dto.response.*;
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
import static com.querydsl.core.group.GroupBy.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<StudentApplicationListResponse> queryStudentApplicationList(UUID studentId) {
        return jpaQueryFactory
                .selectFrom(application)
                .leftJoin(application.student, student)
                .leftJoin(application.recruitment, recruitment)
                .leftJoin(recruitment.company, company)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .where(student.id.eq(studentId))
                .transform(
                        groupBy(application.id)
                                .list(
                                        new QStudentApplicationListResponse(
                                                application.id,
                                                student.name,
                                                company.name,
                                                list(applicationAttachment.attachmentUrl),
                                                application.applicationStatus
                                        )
                                )
                );
    }

    public List<QueryApplicationListResponse> queryApplicationListByCompanyId(UUID recruitmentId) {
        return jpaQueryFactory
                .selectFrom(application)
                .leftJoin(application.student, student)
                .leftJoin(application.recruitment, recruitment)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .where(recruitment.id.eq(recruitmentId))
                .transform(
                        groupBy(application.id)
                                .list(
                                        new QQueryApplicationListResponse(
                                                application.id,
                                                student.name,
                                                student.number,
                                                list(applicationAttachment.attachmentUrl),
                                                application.createdAt,
                                                application.applicationStatus
                                        )
                                )
                );
    }

    public List<QueryCompanyApplicationListResponse> queryCompanyApplicationList(UUID companyId) {
        return jpaQueryFactory
                .selectFrom(application)
                .leftJoin(application.student, student)
                .leftJoin(application.recruitment, recruitment)
                .leftJoin(recruitment.company, company)
                .leftJoin(application.applicationAttachments, applicationAttachment)
                .where(company.id.eq(companyId),
                        application.applicationStatus.ne(ApplicationStatus.REQUESTED))
                .transform(
                        groupBy(application.id)
                                .list(
                                        new QQueryCompanyApplicationListResponse(
                                                student.number,
                                                student.name,
                                                list(applicationAttachment.attachmentUrl),
                                                application.createdAt
                                        )
                                )
                );
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
