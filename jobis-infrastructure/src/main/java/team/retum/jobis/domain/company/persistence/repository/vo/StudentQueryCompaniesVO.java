package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.spi.vo.StudentCompaniesVO;

@Getter
@NoArgsConstructor
public class StudentQueryCompaniesVO extends StudentCompaniesVO {

    @QueryProjection
    public StudentQueryCompaniesVO(Long id, String name, String logoUrl, double take, Long hasRecruitment) {
        super(id, name, logoUrl, take, hasRecruitment != 0);
    }
}
