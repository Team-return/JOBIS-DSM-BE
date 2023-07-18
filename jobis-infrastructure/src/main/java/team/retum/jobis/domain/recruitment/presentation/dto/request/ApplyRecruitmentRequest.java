package team.retum.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.recruitment.model.ProgressType;
import team.retum.jobis.global.annotation.ValidListElements;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentRequest {

    @Valid
    @ValidListElements
    private List<RecruitAreaRequest> areas;

    private String preferentialTreatment;

    private Integer requiredGrade;

    @NotNull
    private int workHours;

    private List<String> requiredLicenses;

    @ValidListElements
    private List<ProgressType> hiringProgress;

    @NotNull
    private int trainPay;

    private Integer pay;

    private String benefits;

    @NotNull
    private boolean militarySupport;

    @NotNull
    private boolean personalContact;

    @NotNull
    private String submitDocument;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private String etc;
}
