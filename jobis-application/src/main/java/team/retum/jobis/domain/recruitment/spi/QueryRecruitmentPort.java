package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.dto.RecruitmentFilter;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.util.List;
import java.util.Optional;

public interface QueryRecruitmentPort {

    boolean existsByCompanyIdAndWinterIntern(Long companyId, boolean winterIntern);

    List<Recruitment> getAll();

    Recruitment getByIdOrThrow(Long recruitmentId);

    Optional<Recruitment> getById(Long recruitmentId);

    Recruitment getRecentByCompanyIdOrThrow(Long companyId);

    RecruitmentDetailVO getByIdAndStudentIdOrThrow(Long recruitmentId, Long studentId);

    List<StudentRecruitmentVO> getStudentRecruitmentsBy(RecruitmentFilter filter);

    List<TeacherRecruitmentVO> getTeacherRecruitmentsBy(RecruitmentFilter filter);

    List<TeacherRecruitmentVO> getTeacherRecruitmentsWithoutPageBy(RecruitmentFilter filter);

    List<Recruitment> getAllByIdInOrThrow(List<Long> recruitmentIds);

    Long getCountBy(RecruitmentFilter filter);

    List<MyAllRecruitmentsVO> getAllByCompanyId(Long companyId);

    Long countRecruitments();
}
