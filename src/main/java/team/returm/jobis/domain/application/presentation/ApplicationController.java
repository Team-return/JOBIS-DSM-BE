package team.returm.jobis.domain.application.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.presentation.dto.request.ChangeApplicationsStatusRequest;
import team.returm.jobis.domain.application.presentation.dto.request.ChangeFieldTrainDateRequest;
import team.returm.jobis.domain.application.presentation.dto.request.CreateApplicationRequest;
import team.returm.jobis.domain.application.presentation.dto.request.RejectApplicationRequest;
import team.returm.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse;
import team.returm.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse;
import team.returm.jobis.domain.application.presentation.dto.response.TeacherQueryApplicationsResponse;
import team.returm.jobis.domain.application.service.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationController {

    private final CreateApplicationService createApplicationService;
    private final DeleteApplicationService deleteApplicationService;
    private final TeacherQueryApplicationsService queryApplicationListService;
    private final QueryCompanyApplicationsService queryCompanyApplicationsService;
    private final QueryStudentApplicationsService queryStudentApplicationsService;
    private final ChangeApplicationsStatusService changeApplicationsStatusService;
    private final ChangeFieldTrainDateService changeFieldTrainDateService;
    private final RejectApplicationService rejectApplicationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}")
    public void createApplication(
            @RequestBody @Valid CreateApplicationRequest request,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        createApplicationService.execute(request, recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{application-id}")
    public void deleteApplication(@PathVariable("application-id") Long applicationId) {
        deleteApplicationService.execute(applicationId);
    }

    @GetMapping
    public TeacherQueryApplicationsResponse queryTeacherApplicationList(
            @RequestParam(value = "application_status", required = false) ApplicationStatus applicationStatus,
            @RequestParam(value = "student_name", required = false) String studentName,
            @RequestParam(value = "recruitment_id", required = false) Long recruitmentId
    ) {
        return queryApplicationListService.execute(applicationStatus, studentName, recruitmentId);
    }

    @GetMapping("/company")
    public CompanyQueryApplicationsResponse queryCompanyApplicationList() {
        return queryCompanyApplicationsService.execute();
    }

    @GetMapping("/students")
    public StudentQueryApplicationsResponse queryApplication() {
        return queryStudentApplicationsService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/status")
    public void changeApplicationsStatus(@RequestBody @Valid ChangeApplicationsStatusRequest request) {
        changeApplicationsStatusService.execute(
                request.getApplicationIds(),
                request.getStatus()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/train-date")
    public void changeFieldTrainDate(@RequestBody @Valid ChangeFieldTrainDateRequest request) {
        changeFieldTrainDateService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{application-id}")
    public void rejectApplication(
            @PathVariable("application-id") Long applicationId,
            @Valid @RequestBody RejectApplicationRequest request
    ) {
        rejectApplicationService.execute(applicationId, request.getReason());
    }
}
