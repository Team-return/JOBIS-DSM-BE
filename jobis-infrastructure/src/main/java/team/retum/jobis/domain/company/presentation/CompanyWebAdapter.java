package team.retum.jobis.domain.company.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.company.dto.response.CheckCompanyExistsResponse;
import team.retum.jobis.domain.company.dto.response.CompanyMyPageResponse;
import team.retum.jobis.domain.company.dto.response.QueryCompanyDetailsResponse;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse;
import team.retum.jobis.domain.company.dto.response.StudentQueryCompaniesResponse;
import team.retum.jobis.domain.company.dto.response.TeacherQueryCompaniesResponse;
import team.retum.jobis.domain.company.dto.response.TeacherQueryEmployCompaniesResponse;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.presentation.dto.request.RegisterCompanyWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyTypeWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateMouWebRequest;
import team.retum.jobis.domain.company.usecase.CheckCompanyExistsUseCase;
import team.retum.jobis.domain.company.usecase.CompanyMyPageUseCase;
import team.retum.jobis.domain.company.usecase.QueryCompanyDetailsUseCase;
import team.retum.jobis.domain.company.usecase.QueryReviewAvailableCompaniesUseCase;
import team.retum.jobis.domain.company.usecase.RegisterCompanyUseCase;
import team.retum.jobis.domain.company.usecase.StudentQueryCompaniesUseCase;
import team.retum.jobis.domain.company.usecase.TeacherQueryCompaniesUseCase;
import team.retum.jobis.domain.company.usecase.TeacherQueryEmployCompaniesUseCase;
import team.retum.jobis.domain.company.usecase.UpdateCompanyDetailsUseCase;
import team.retum.jobis.domain.company.usecase.UpdateCompanyTypeUseCase;
import team.retum.jobis.domain.company.usecase.UpdateMouUseCase;

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
    private final UpdateMouUseCase updateMouUseCase;
    private final QueryReviewAvailableCompaniesUseCase queryReviewAvailableCompaniesUseCase;

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
    @PatchMapping("/{company-id}")
    public void updateDetails(
            @RequestBody @Valid UpdateCompanyDetailsWebRequest request,
            @PathVariable("company-id") Long companyId
    ) {
        updateCompanyDetailsUseCase.execute(request.toDomainRequest(), companyId);
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
        updateMouUseCase.execute(request.toDomainRequest());
    }

    @GetMapping("/review")
    public QueryReviewAvailableCompaniesResponse queryReviewAvailableCompanies() {
        return queryReviewAvailableCompaniesUseCase.execute();
    }
}
