package com.example.jobis.domain.application.controller;

import com.example.jobis.domain.application.controller.dto.request.CreateApplicationRequest;
import com.example.jobis.domain.application.controller.dto.response.QueryApplicationDetailsResponse;
import com.example.jobis.domain.application.controller.dto.response.QueryApplicationListResponse;
import com.example.jobis.domain.application.service.CreateApplicationService;
import com.example.jobis.domain.application.service.DeleteApplicationService;
import com.example.jobis.domain.application.service.QueryApplicationDetailsService;
import com.example.jobis.domain.application.service.QueryApplicationListService;
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
    private final QueryApplicationListService queryApplicationListService;
    private final QueryApplicationDetailsService queryApplicationDetailsService;

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

    @GetMapping("/{company-id}")
    public List<QueryApplicationListResponse> queryApplicationList(@PathVariable("company-id") UUID companyId) {
        return queryApplicationListService.execute(companyId);
    }

    @GetMapping("/details/{application-id}")
    public QueryApplicationDetailsResponse queryApplicationDetails(@PathVariable("application-id") UUID applicationId) {
        return queryApplicationDetailsService.execute(applicationId);
    }
}
