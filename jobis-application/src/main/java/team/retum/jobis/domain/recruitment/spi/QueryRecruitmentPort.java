package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QueryRecruitmentPort {
    boolean existsOnRecruitmentByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern);

    List<Recruitment> queryAllRecruitments();

    Optional<Recruitment> queryRecruitmentById(Long recruitmentId);

    Optional<RecruitArea> queryRecruitmentAreaById(Long recruitAreaId);

    Long queryRecruitmentAreaCountByRecruitmentId(Long recruitmentId);

    Optional<Recruitment> queryRecentRecruitmentByCompanyId(Long companyId);

    RecruitmentDetailVO queryRecruitmentDetailByIdAndStudentId(Long recruitmentId, Long studentId);

    List<StudentRecruitmentVO> queryStudentRecruitmentsByFilter(RecruitmentFilter filter);

    List<TeacherRecruitmentVO> queryTeacherRecruitmentsByFilter(RecruitmentFilter filter);

    List<Recruitment> queryRecruitmentsByIdIn(List<Long> recruitmentIds);

    Long getRecruitmentCountByFilter(RecruitmentFilter filter);

    List<RecruitAreaResponse> queryRecruitAreasByRecruitmentId(Long recruitmentId);

    Map<Long, String> queryCompanyNameByRecruitmentIds(List<Long> recruitmentIds);

    List<MyAllRecruitmentsVO> queryMyAllRecruitmentsVOByCompanyId(Long companyId);
}
