package team.returm.jobis.domain.recruitment.presentation.dto.request;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.enums.ProgressType;

@Getter
@NoArgsConstructor
public class UpdateRecruitmentRequest {

    private String preferentialTreatment;
    private Integer requiredGrade;
    @NotNull
    private Integer workHours;
    private List<String> requiredLicenses;
    @NotNull
    private List<ProgressType> hiringProgress;
    @NotNull
    private Integer trainPay;
    private Integer pay;
    private String benefits;
    @NotNull
    private boolean military;
    @NotNull
    private String submitDocument;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private String etc;
}
