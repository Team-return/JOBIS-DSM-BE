package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.model.RecruitArea;

import java.util.List;

public interface CommandRecruitAreaPort {

    RecruitArea save(RecruitArea recruitArea);

    void saveAll(List<RecruitArea> recruitAreas);

    void delete(RecruitArea recruitArea);
}
