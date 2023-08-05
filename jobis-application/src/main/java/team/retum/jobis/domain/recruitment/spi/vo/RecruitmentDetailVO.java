package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitmentDetailVO {

    private final Long companyId;
    private final String companyProfileUrl;
    private final String companyName;
    private final String preferentialTreatment;
    private final Integer requiredGrade;
    private final Integer workHours;
    private final List<String> requiredLicenses;

    private final List<ProgressType> hiringProgress;
    private final Integer trainPay;
    private final Integer pay;
    private final String benefits;
    private final Boolean military;
    private final String submitDocument;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;
}
