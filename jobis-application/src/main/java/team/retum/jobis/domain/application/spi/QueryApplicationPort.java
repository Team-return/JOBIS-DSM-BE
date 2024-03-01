package team.retum.jobis.domain.application.spi;

import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.dto.ApplicationFilter;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;
import team.retum.jobis.domain.application.spi.vo.PassedApplicationStudentsVO;

import java.util.List;
import java.util.Optional;

public interface QueryApplicationPort {

    List<ApplicationVO> queryApplicationByConditions(ApplicationFilter applicationFilter);

    Long queryApplicationCountByCondition(ApplicationStatus applicationStatus, String studentName);

    List<FieldTraineesVO> queryApplicationsFieldTraineesByCompanyId(Long companyId);

    List<PassedApplicationStudentsVO> queryPassedApplicationStudentsByCompanyId(Long companyId);

    List<Application> queryApplicationsByIds(List<Long> applicationIds);

    Long queryApplicationCountByIds(List<Long> applicationIds);

    List<ApplicationDetailVO> queryApplicationDetailsByIds(List<Long> applicationIds);

    Optional<Application> queryApplicationById(Long applicationId);

    Optional<Application> queryApplicationByIdAndApplicationStatus(Long applicationId, ApplicationStatus applicationStatus);

    Optional<Application> queryApplicationByCompanyIdAndStudentId(Long applicationId, Long studentId);

    boolean existsApplicationByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);

    boolean existsApplicationByStudentIdAndRecruitmentId(Long studentId, Long recruitmentId);
}
