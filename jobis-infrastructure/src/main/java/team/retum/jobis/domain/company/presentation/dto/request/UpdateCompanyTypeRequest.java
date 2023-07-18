package team.retum.jobis.domain.company.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.company.model.CompanyType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateCompanyTypeRequest {

    @NotNull
    private List<Long> companyIds;

    @NotNull
    private CompanyType companyType;
}
