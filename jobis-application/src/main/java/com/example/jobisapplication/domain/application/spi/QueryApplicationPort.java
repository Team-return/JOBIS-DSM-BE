package com.example.jobisapplication.domain.application.spi;

import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.spi.vo.ApplicationDetailVO;
import com.example.jobisapplication.domain.application.spi.vo.ApplicationVO;
import com.example.jobisapplication.domain.application.spi.vo.FieldTraineesVO;
import com.example.jobisapplication.domain.application.spi.vo.PassedApplicationStudentsVO;
import com.example.jobisapplication.domain.application.spi.vo.TotalApplicationCountVO;

import java.util.List;

public interface QueryApplicationPort {


    List<ApplicationVO> queryApplicationByConditions(Long recruitmentId, Long studentId, ApplicationStatus applicationStatus, String studentName);

    Long queryApplicationCountByCondition(ApplicationStatus applicationStatus, String studentName);

    List<FieldTraineesVO> queryApplicationsFieldTraineesByCompanyId(Long companyId);

    List<PassedApplicationStudentsVO> queryPassedApplicationStudentsByCompanyId(Long companyId);

    TotalApplicationCountVO queryTotalApplicationCount();

    List<Application> queryApplicationsByIds(List<Long> applicationIds);

    List<ApplicationDetailVO> queryApplicationDetailsByIds(List<Long> applicationIds);

    Application queryApplicationById(Long applicationId);

    boolean existsApplicationByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);

}
