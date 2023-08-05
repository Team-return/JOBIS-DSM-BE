package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;

@Getter
public class StudentQueryCompaniesVO extends StudentCompaniesVO {

    @QueryProjection
    public StudentQueryCompaniesVO(Long id, String name, String logoUrl, double take, Long hasRecruitment) {
        super(id, name, logoUrl, take, hasRecruitment != 0);
    }
}
