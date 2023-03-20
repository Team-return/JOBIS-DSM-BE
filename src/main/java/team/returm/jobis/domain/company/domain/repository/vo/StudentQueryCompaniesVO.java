package team.returm.jobis.domain.company.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import java.util.UUID;
import lombok.Getter;

@Getter
public class StudentQueryCompaniesVO {
    private final UUID id;
    private final String name;
    private final String logoUrl;
    private final Integer take;

    @QueryProjection
    public StudentQueryCompaniesVO(UUID id, String name, String logoUrl, Integer take) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.take = take;
    }
}
