package com.example.jobis.domain.application.controller;

import com.example.jobis.domain.application.controller.dto.request.CreateApplicationRequest;
import com.example.jobis.domain.application.controller.dto.response.TeacherQueryApplicationsResponse;
import com.example.jobis.domain.application.controller.dto.response.QueryCompanyApplicationsResponse;
import com.example.jobis.domain.application.controller.dto.response.StudentApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationController {

    private final CreateApplicationService createApplicationService;
    private final DeleteApplicationService deleteApplicationService;
    private final TeacherQueryApplicationsService queryApplicationListService;
    private final QueryCompanyApplicationsService queryCompanyApplicationsService;
    private final QueryStudentApplicationsService queryStudentApplicationsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}")
    public void createApplication(@RequestBody @Valid CreateApplicationRequest request, @PathVariable("recruitment-id") UUID recruitmentId) {
        createApplicationService.execute(request, recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{application-id}")
    public void deleteApplication(@PathVariable("application-id") UUID applicationId) {
        deleteApplicationService.execute(applicationId);
    }

    @GetMapping
    public List<TeacherQueryApplicationsResponse> queryTeacherApplicationList (
            @RequestParam(value = "recruitment-id", required = false) UUID recruitmentId,
            @RequestParam(value = "application-status", required = false) ApplicationStatus applicationStatus,
            @RequestParam(value = "student-name", required = false) String studentName
            ) {
        return queryApplicationListService.execute(recruitmentId, applicationStatus, studentName);
    }

    @GetMapping("/company")
    public List<QueryCompanyApplicationsResponse> queryCompanyApplicationList() {
        return queryCompanyApplicationsService.execute();
    }

    @GetMapping("/students")
    public List<StudentApplicationsResponse> queryApplication() {
        return queryStudentApplicationsService.execute();
    }
}
