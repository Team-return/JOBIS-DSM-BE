package team.returm.jobis.domain.company.presentation;

import team.returm.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsRequest;
import team.returm.jobis.domain.company.presentation.dto.response.CompanyDetailsResponse;
import team.returm.jobis.domain.company.presentation.dto.response.StudentQueryCompaniesResponse;
import team.returm.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
import team.returm.jobis.domain.company.presentation.dto.response.CheckCompanyExistsResponse;
import team.returm.jobis.domain.company.service.CheckCompanyExistsService;
import team.returm.jobis.domain.company.service.CompanyMyPageService;
import team.returm.jobis.domain.company.service.QueryCompanyDetailsService;
import team.returm.jobis.domain.company.service.RegisterCompanyService;
import team.returm.jobis.domain.company.service.StudentQueryCompaniesService;
import team.returm.jobis.domain.company.service.UpdateCompanyDetailsService;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import team.returm.jobis.domain.company.presentation.dto.request.RegisterCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final RegisterCompanyService registerCompanyService;
    private final CheckCompanyExistsService checkCompanyExistsService;
    private final UpdateCompanyDetailsService updateCompanyDetailsService;
    private final StudentQueryCompaniesService studentQueryCompaniesService;
    private final QueryCompanyDetailsService queryCompanyDetailsService;
    private final CompanyMyPageService companyMyPageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse register(@RequestBody @Valid RegisterCompanyRequest request) {
        return registerCompanyService.execute(request);
    }

    @GetMapping("/exists/{business-number}")
    public CheckCompanyExistsResponse companyExists(@PathVariable("business-number") String businessNumber) {
        return checkCompanyExistsService.execute(businessNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateDetails(@RequestBody @Valid UpdateCompanyDetailsRequest request) {
        updateCompanyDetailsService.execute(request);
    }

    @GetMapping("/student")
    public StudentQueryCompaniesResponse studentQueryCompanies() {
        return studentQueryCompaniesService.execute();
    }

    @GetMapping("/{company-id}")
    public CompanyDetailsResponse getCompanyDetails(@PathVariable("company-id") UUID companyId) {
        return queryCompanyDetailsService.execute(companyId);
    }

    @GetMapping("/my")
    public CompanyMyPageResponse queryMyPage() {
        return companyMyPageService.execute();
    }
}
