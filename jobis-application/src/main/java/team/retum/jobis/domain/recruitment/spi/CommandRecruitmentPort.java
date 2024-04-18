package team.retum.jobis.domain.recruitment.spi;

import team.retum.jobis.domain.recruitment.model.Recruitment;

import java.util.List;

public interface CommandRecruitmentPort {

    Recruitment save(Recruitment recruitment);

    void saveAll(List<Recruitment> recruitments);

    void delete(Recruitment recruitment);
}
