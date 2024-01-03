package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TeacherEmployCompaniesVO {

    private final Long companyId;
    private final String companyName;
    private final Long fieldTraineeCount;
    private final Long contractCount;
}
