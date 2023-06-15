package team.returm.jobis.domain.company.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class StudentQueryCompaniesVO {
    private final Long id;
    private final String name;
    private final String logoUrl;
    private final double take;
    private final boolean hasRecruitment;

    @QueryProjection
    public StudentQueryCompaniesVO(Long id, String name, String logoUrl, double take, Long hasRecruitment) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.take = take;
        this.hasRecruitment = hasRecruitment != 0;
    }
}
