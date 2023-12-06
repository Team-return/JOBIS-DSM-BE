package team.retum.jobis.domain.application.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.domain.application.dto.response.CompanyQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.QueryEmploymentCountResponse;
import team.retum.jobis.domain.application.dto.response.QueryPassedApplicationStudentsResponse;
import team.retum.jobis.domain.application.dto.response.QueryRejectionReasonResponse;
import team.retum.jobis.domain.application.dto.response.QueryMyApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.presentation.dto.request.ChangeApplicationsStatusWebRequest;
import team.retum.jobis.domain.application.presentation.dto.request.ChangeFieldTrainDateWebRequest;
import team.retum.jobis.domain.application.presentation.dto.request.CreateApplicationWebRequest;
import team.retum.jobis.domain.application.presentation.dto.request.RejectApplicationWebRequest;
import team.retum.jobis.domain.application.usecase.ChangeApplicationsStatusUseCase;
import team.retum.jobis.domain.application.usecase.ChangeFieldTrainDateUseCase;
import team.retum.jobis.domain.application.usecase.CompanyQueryApplicationsUseCase;
import team.retum.jobis.domain.application.usecase.CreateApplicationUseCase;
import team.retum.jobis.domain.application.usecase.DeleteApplicationUseCase;
import team.retum.jobis.domain.application.usecase.QueryEmploymentCountUseCase;
import team.retum.jobis.domain.application.usecase.QueryPassedApplicationStudentsUseCase;
import team.retum.jobis.domain.application.usecase.QueryRejectionReasonUseCase;
import team.retum.jobis.domain.application.usecase.QueryMyApplicationsUseCase;
import team.retum.jobis.domain.application.usecase.ReapplyUseCase;
import team.retum.jobis.domain.application.usecase.RejectApplicationUseCase;
import team.retum.jobis.domain.application.usecase.TeacherQueryApplicationsUseCase;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationWebAdapter {

    private final CreateApplicationUseCase createApplicationUseCase;
    private final DeleteApplicationUseCase deleteApplicationUseCase;
    private final TeacherQueryApplicationsUseCase queryApplicationListService;
    private final CompanyQueryApplicationsUseCase companyQueryApplicationsUseCase;
    private final QueryMyApplicationsUseCase queryMyApplicationsUseCase;
    private final ChangeApplicationsStatusUseCase changeApplicationsStatusUseCase;
    private final ChangeFieldTrainDateUseCase changeFieldTrainDateUseCase;
    private final RejectApplicationUseCase rejectApplicationUseCase;
    private final QueryEmploymentCountUseCase queryEmploymentCountUseCase;
    private final QueryPassedApplicationStudentsUseCase queryPassedApplicationStudentsUseCase;
    private final ReapplyUseCase reapplyUseCase;
    private final QueryRejectionReasonUseCase queryRejectionReasonUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}")
    public void createApplication(
            @RequestBody @Valid CreateApplicationWebRequest request,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        createApplicationUseCase.execute(request.toDomainRequest(), recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{application-id}")
    public void deleteApplication(@PathVariable("application-id") Long applicationId) {
        deleteApplicationUseCase.execute(applicationId);
    }

    @GetMapping
    public TeacherQueryApplicationsResponse queryTeacherApplicationList(
            @RequestParam(value = "application_status", required = false) ApplicationStatus applicationStatus,
            @RequestParam(value = "student_name", required = false) String studentName,
            @RequestParam(value = "recruitment_id", required = false) Long recruitmentId,
            @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Long page
    ) {
        return queryApplicationListService.execute(applicationStatus, studentName, recruitmentId, page - 1);
    }

    @GetMapping("/count")
    public TotalPageCountResponse queryApplicationCount(
            @RequestParam(value = "application_status", required = false) ApplicationStatus applicationStatus,
            @RequestParam(value = "student_name", required = false) String studentName
    ) {
        return queryApplicationListService.getTotalPageCount(applicationStatus, studentName);
    }

    @GetMapping("/company")
    public CompanyQueryApplicationsResponse queryCompanyApplicationList(
            @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Long page
    ) {
        return companyQueryApplicationsUseCase.execute(page - 1);
    }

    @GetMapping("/students")
    public QueryMyApplicationsResponse queryMyApplications(
            @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Long page
    ) {
        return queryMyApplicationsUseCase.execute(page - 1);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/status")
    public void changeApplicationsStatus(@RequestBody @Valid ChangeApplicationsStatusWebRequest request) {
        changeApplicationsStatusUseCase.execute(
                request.getApplicationIds(),
                request.getStatus()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/train-date")
    public void changeFieldTrainDate(@RequestBody @Valid ChangeFieldTrainDateWebRequest request) {
        changeFieldTrainDateUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/rejection/{application-id}")
    public void rejectApplication(
            @PathVariable("application-id") Long applicationId,
            @Valid @RequestBody RejectApplicationWebRequest request
    ) {
        rejectApplicationUseCase.execute(applicationId, request.getReason());
    }

    @GetMapping("/employment/count")
    public QueryEmploymentCountResponse queryEmploymentCount() {
        return queryEmploymentCountUseCase.execute();
    }

    @GetMapping("/pass/{company-id}")
    public QueryPassedApplicationStudentsResponse queryFieldTrainApplication(
            @PathVariable("company-id") Long companyId
    ) {
        return queryPassedApplicationStudentsUseCase.execute(companyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{application-id}")
    public void reapply(
            @PathVariable("application-id") Long applicationId,
            @RequestBody CreateApplicationWebRequest request
    ) {
        reapplyUseCase.execute(applicationId, request.toDomainRequest());
    }

    @GetMapping("/rejection/{application-id}")
    public QueryRejectionReasonResponse queryRejectionReason(@PathVariable("application-id") Long applicationId) {
        return queryRejectionReasonUseCase.execute(applicationId);
    }
}
