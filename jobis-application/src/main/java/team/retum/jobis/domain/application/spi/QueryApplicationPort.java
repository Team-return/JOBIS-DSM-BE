package team.retum.jobis.domain.application.spi;

import team.retum.jobis.domain.application.dto.ApplicationFilter;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;
import team.retum.jobis.domain.application.spi.vo.PassedApplicationStudentsVO;

import java.util.List;

public interface QueryApplicationPort {

    List<ApplicationVO> getAllByConditions(ApplicationFilter applicationFilter);

    Long getCountByCondition(ApplicationStatus applicationStatus, String studentName);

    List<FieldTraineesVO> getFieldTraineesByCompanyId(Long companyId);

    List<PassedApplicationStudentsVO> getPassedStudentsByCompanyId(Long companyId);

    List<Application> getAllByIdInOrThrow(List<Long> applicationIds);

    List<ApplicationDetailVO> getDetailsByIds(List<Long> applicationIds);

    Application getByIdOrThrow(Long applicationId);

    Application getByIdAndApplicationStatusOrThrow(Long applicationId, ApplicationStatus applicationStatus);

    Application getByCompanyIdAndStudentIdOrThrow(Long applicationId, Long studentId);

    boolean existsByStudentIdAndApplicationStatusIn(Long studentId, List<ApplicationStatus> applicationStatuses);

    boolean existsByStudentIdAndRecruitmentId(Long studentId, Long recruitmentId);

    List<ApplicationStatus> getApplicationStatusByStudentId(Long studentId);
}
