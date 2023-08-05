package team.retum.jobis.domain.application.spi;

import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;
import team.retum.jobis.domain.application.spi.vo.PassedApplicationStudentsVO;
import team.retum.jobis.domain.application.spi.vo.TotalApplicationCountVO;

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
