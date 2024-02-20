package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;

import java.util.List;

public interface CommandRecruitmentPort {
    Recruitment saveRecruitment(Recruitment recruitment);

    RecruitArea saveRecruitmentArea(RecruitArea recruitArea);

    void saveAllRecruitmentAreas(List<RecruitArea> recruitAreas);

    void saveAllRecruitments(List<Recruitment> recruitments);

    void deleteRecruitAreaById(Long recruitAreaId);

    void deleteRecruitment(Recruitment recruitment);
}
