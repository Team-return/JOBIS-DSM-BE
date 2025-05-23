package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.StudentRecruitmentFilter;
import team.retum.jobis.domain.recruitment.dto.response.RecruitmentExistsResponse;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.ManualRecruitmentVO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface QueryRecruitmentPort {

    boolean existsByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern);

    List<Recruitment> getAll();

    Recruitment getByIdOrThrow(Long recruitmentId);

    Optional<Recruitment> getById(Long recruitmentId);

    Recruitment getRecentByCompanyIdOrThrow(Long companyId);

    RecruitmentDetailVO getByIdAndStudentIdOrThrow(Long recruitmentId, Long studentId);

    List<StudentRecruitmentVO> getStudentRecruitmentsBy(StudentRecruitmentFilter filter);

    List<StudentRecruitmentVO> getStudentRecruitmentByCompanyNames(List<String> companyName, Long studentId);

    List<TeacherRecruitmentVO> getTeacherRecruitmentsBy(RecruitmentFilter filter);

    List<TeacherRecruitmentVO> getTeacherRecruitmentsWithoutPageBy(RecruitmentFilter filter);

    List<Recruitment> getAllByIdInOrThrow(List<Long> recruitmentIds);

    Long getCountBy(RecruitmentFilter filter);

    List<MyAllRecruitmentsVO> getAllByCompanyId(Long companyId);

    List<Recruitment> getRecent();

    RecruitmentExistsResponse existsByCompanyId(Long companyId);

    List<ManualRecruitmentVO> getTeacherManualRecruitments();

    List<Recruitment> getByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern);
}
