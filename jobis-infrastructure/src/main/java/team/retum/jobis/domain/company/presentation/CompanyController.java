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
import com.example.jobisapplication.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.presentation.dto.request.RegisterCompanyWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyDetailsWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyTypeWebRequest;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateMouWebRequest;
import com.example.jobisapplication.domain.company.dto.response.CheckCompanyExistsResponse;
import com.example.jobisapplication.domain.company.dto.response.CompanyMyPageResponse;
import com.example.jobisapplication.domain.company.dto.response.QueryCompanyDetailsResponse;
import com.example.jobisapplication.domain.company.dto.response.StudentQueryCompaniesResponse;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryCompaniesResponse;
import com.example.jobisapplication.domain.company.dto.response.TeacherQueryEmployCompaniesResponse;
import team.retum.jobis.domain.company.service.CheckCompanyExistsService;
import team.retum.jobis.domain.company.service.CompanyMyPageService;
import team.retum.jobis.domain.company.service.QueryCompanyDetailsService;
import team.retum.jobis.domain.company.service.RegisterCompanyService;
import team.retum.jobis.domain.company.service.StudentQueryCompaniesService;
import team.retum.jobis.domain.company.service.TeacherQueryCompaniesService;
import team.retum.jobis.domain.company.service.TeacherQueryEmployCompaniesService;
import team.retum.jobis.domain.company.service.UpdateCompanyDetailsService;
import team.retum.jobis.domain.company.service.UpdateCompanyTypeService;
import team.retum.jobis.domain.company.service.UpdateConventionService;
import com.example.jobisapplication.domain.user.dto.response.TokenResponse;

import javax.validation.Valid;

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
    private final UpdateCompanyTypeService updateCompanyTypeService;
    private final TeacherQueryEmployCompaniesService teacherQueryEmployCompaniesService;
    private final TeacherQueryCompaniesService teacherQueryCompaniesService;
    private final UpdateConventionService updateConventionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse register(@RequestBody @Valid RegisterCompanyWebRequest request) {
        return registerCompanyService.execute(request);
    }

    @GetMapping("/exists/{business-number}")
    public CheckCompanyExistsResponse companyExists(@PathVariable("business-number") String businessNumber) {
        return checkCompanyExistsService.execute(businessNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateDetails(@RequestBody @Valid UpdateCompanyDetailsWebRequest request) {
        updateCompanyDetailsService.execute(request);
    }

    @GetMapping("/student")
    public StudentQueryCompaniesResponse studentQueryCompanies(
            @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(value = "name", required = false) String name
    ) {
        return studentQueryCompaniesService.execute(page - 1, name);
    }

    @GetMapping("/{company-id}")
    public QueryCompanyDetailsResponse getCompanyDetails(@PathVariable("company-id") Long companyId) {
        return queryCompanyDetailsService.execute(companyId);
    }

    @GetMapping("/my")
    public CompanyMyPageResponse queryMyPage() {
        return companyMyPageService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/type")
    public void updateCompanyType(@RequestBody @Valid UpdateCompanyTypeWebRequest request) {
        updateCompanyTypeService.execute(request);
    }

    @GetMapping("/employment")
    public TeacherQueryEmployCompaniesResponse queryEmployCompanies(
            @RequestParam(value = "company_name", required = false) String companyName,
            @RequestParam(value = "company_type", required = false) CompanyType type,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        return teacherQueryEmployCompaniesService.execute(companyName, type, year);
    }

    @GetMapping("/teacher")
    public TeacherQueryCompaniesResponse queryCompanies(
            @RequestParam(value = "type", required = false) CompanyType type,
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "business_area", required = false) Long businessArea,
            @RequestParam(value = "page", defaultValue = "1") Long page
    ) {
        return teacherQueryCompaniesService.execute(type, companyName, region, businessArea, page - 1);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/mou")
    public void updateMou(@RequestBody @Valid UpdateMouWebRequest request) {
        updateConventionService.execute(request);
    }
}
