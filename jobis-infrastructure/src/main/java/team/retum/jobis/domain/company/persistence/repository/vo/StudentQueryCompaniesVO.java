package team.retum.jobis.domain.company.persistence.repository.vo;

import com.example.jobisapplication.domain.company.spi.vo.StudentCompaniesVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class StudentQueryCompaniesVO extends StudentCompaniesVO {

    @QueryProjection
    public StudentQueryCompaniesVO(Long id, String name, String logoUrl, double take, Long hasRecruitment) {
        super(id, name, logoUrl, take, hasRecruitment != 0);
    }
}
