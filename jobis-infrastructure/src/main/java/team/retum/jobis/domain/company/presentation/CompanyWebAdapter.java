package team.retum.jobis.domain.company.presentation;

import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.company.dto.request.RegisterCompanyRequest;
import com.example.jobisapplication.domain.company.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobisapplication.domain.company.dto.request.UpdateCompanyTypeRequest;
import com.example.jobisapplication.domain.company.dto.request.UpdateMouRequest;
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
import com.example.jobisapplication.domain.company.usecase.CheckCompanyExistsUseCase;
import com.example.jobisapplication.domain.company.usecase.CompanyMyPageUseCase;
import com.example.jobisapplication.domain.company.usecase.QueryCompanyDetailsUseCase;
import com.example.jobisapplication.domain.company.usecase.RegisterCompanyUseCase;
import com.example.jobisapplication.domain.company.usecase.StudentQueryCompaniesUseCase;
import com.example.jobisapplication.domain.company.usecase.TeacherQueryCompaniesUseCase;
import com.example.jobisapplication.domain.company.usecase.TeacherQueryEmployCompaniesUseCase;
import com.example.jobisapplication.domain.company.usecase.UpdateCompanyDetailsUseCase;
import com.example.jobisapplication.domain.company.usecase.UpdateCompanyTypeUseCase;
import com.example.jobisapplication.domain.company.usecase.UpdateConventionUseCase;

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
        return registerCompanyUseCase.execute(
                RegisterCompanyRequest.builder()
                        .name(request.getName())
                        .businessNumber(request.getBusinessNumber())
                        .password(request.getPassword())
                        .companyIntroduce(request.getCompanyIntroduce())
                        .mainZipCode(request.getMainZipCode())
                        .mainAddress(request.getMainAddress())
                        .mainAddressDetail(request.getMainAddressDetail())
                        .subZipCode(request.getSubZipCode())
                        .subAddress(request.getSubAddress())
                        .subAddressDetail(request.getSubAddressDetail())
                        .managerName(request.getManagerName())
                        .managerPhoneNo(request.getManagerPhoneNo())
                        .subManagerName(request.getSubManagerName())
                        .subManagerPhoneNo(request.getSubManagerPhoneNo())
                        .fax(request.getFax())
                        .email(request.getEmail())
                        .representativeName(request.getRepresentativeName())
                        .foundedAt(request.getFoundedAt())
                        .workerNumber(request.getWorkerNumber())
                        .take(request.getTake())
                        .companyProfileUrl(request.getCompanyProfileUrl())
                        .bizRegistrationUrl(request.getBizRegistrationUrl())
                        .businessAreaCode(request.getBusinessAreaCode())
                        .serviceName(request.getServiceName())
                        .attachmentUrls(request.getAttachmentUrls())
                        .build()
        );
    }

    @GetMapping("/exists/{business-number}")
    public CheckCompanyExistsResponse companyExists(@PathVariable("business-number") String businessNumber) {
        return checkCompanyExistsUseCase.execute(businessNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateDetails(@RequestBody @Valid UpdateCompanyDetailsWebRequest request) {
        updateCompanyDetailsUseCase.execute(
                UpdateCompanyDetailsRequest.builder()
                        .companyIntroduce(request.getCompanyIntroduce())
                        .zipCode1(request.getZipCode1())
                        .address1(request.getAddress1())
                        .zipCode2(request.getZipCode2())
                        .address2(request.getAddress2())
                        .manager1(request.getManager1())
                        .phoneNumber1(request.getPhoneNumber1())
                        .manager2(request.getManager2())
                        .phoneNumber2(request.getPhoneNumber2())
                        .fax(request.getFax())
                        .email(request.getEmail())
                        .workerNumber(request.getWorkerNumber())
                        .take(request.getTake())
                        .companyProfileUrl(request.getCompanyProfileUrl())
                        .build()
        );
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
        updateCompanyTypeUseCase.execute(
                new UpdateCompanyTypeRequest(request.getCompanyIds(), request.getCompanyType())
        );
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
        updateConventionUseCase.execute(
                new UpdateMouRequest(request.getCompanyIds())
        );
    }
}
