package team.retum.jobis.domain.company.presentation;

import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.company.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobisapplication.domain.company.dto.request.UpdateCompanyTypeRequest;
import com.example.jobisapplication.domain.company.dto.request.UpdateMouRequest;
import com.example.jobisapplication.domain.company.dto.response.*;
import com.example.jobisapplication.domain.company.model.CompanyType;
import com.example.jobisapplication.domain.company.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.retum.jobis.domain.company.presentation.dto.request.RegisterCompanyWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyTypeWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateMouWebRequest;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyWebAdapter {

    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final CheckCompanyExistsUseCase checkCompanyExistsUseCase;
    private final UpdateCompanyDetailsUseCase updateCompanyDetailsUseCase;
    private final StudentQueryCompaniesUseCase studentQueryCompaniesUseCase;
    private final QueryCompanyDetailsUseCase queryCompanyDetailsUseCase;
    private final CompanyMyPageUseCase companyMyPageUseCase;
    private final UpdateCompanyTypeUseCase updateCompanyTypeUseCase;
    private final TeacherQueryEmployCompaniesUseCase teacherQueryEmployCompaniesUseCase;
    private final TeacherQueryCompaniesUseCase teacherQueryCompaniesUseCase;
    private final UpdateConventionUseCase updateConventionUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse register(@RequestBody @Valid RegisterCompanyWebRequest request) {
        return registerCompanyUseCase.execute(request.toDomainRequest());
    }

    @GetMapping("/exists/{business-number}")
    public CheckCompanyExistsResponse companyExists(@PathVariable("business-number") String businessNumber) {
        return checkCompanyExistsUseCase.execute(businessNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateDetails(@RequestBody @Valid UpdateCompanyDetailsWebRequest request) {
        updateCompanyDetailsUseCase.execute(request.toDomainRequest());
    }

    @GetMapping("/student")
    public StudentQueryCompaniesResponse studentQueryCompanies(
            @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(value = "name", required = false) String name
    ) {
        return studentQueryCompaniesUseCase.execute(page - 1, name);
    }

    @GetMapping("/{company-id}")
    public QueryCompanyDetailsResponse getCompanyDetails(@PathVariable("company-id") Long companyId) {
        return queryCompanyDetailsUseCase.execute(companyId);
    }

    @GetMapping("/my")
    public CompanyMyPageResponse queryMyPage() {
        return companyMyPageUseCase.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/type")
    public void updateCompanyType(@RequestBody @Valid UpdateCompanyTypeWebRequest request) {
        updateCompanyTypeUseCase.execute(request.toDomainRequest());
    }

    @GetMapping("/employment")
    public TeacherQueryEmployCompaniesResponse queryEmployCompanies(
            @RequestParam(value = "company_name", required = false) String companyName,
            @RequestParam(value = "company_type", required = false) CompanyType type,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        return teacherQueryEmployCompaniesUseCase.execute(companyName, type, year);
    }

    @GetMapping("/teacher")
    public TeacherQueryCompaniesResponse queryCompanies(
            @RequestParam(value = "type", required = false) CompanyType type,
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "business_area", required = false) Long businessArea,
            @RequestParam(value = "page", defaultValue = "1") Long page
    ) {
        return teacherQueryCompaniesUseCase.execute(type, companyName, region, businessArea, page - 1);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/mou")
    public void updateMou(@RequestBody @Valid UpdateMouWebRequest request) {
        updateConventionUseCase.execute(request.toDomainRequest());
    }
}
