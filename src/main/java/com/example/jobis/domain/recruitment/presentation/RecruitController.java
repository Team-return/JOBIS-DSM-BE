package com.example.jobis.domain.recruitment.presentation;

import com.example.jobis.domain.recruitment.presentation.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.presentation.dto.response.StudentRecruitDetailsResponse;
import com.example.jobis.domain.recruitment.service.ApplyRecruitmentService;
import com.example.jobis.domain.recruitment.service.ChangeRecruitmentStatusService;
import com.example.jobis.domain.recruitment.service.CreateRecruitAreaService;
import com.example.jobis.domain.recruitment.service.StudentQueryRecruitmentDetailService;
import com.example.jobis.domain.recruitment.service.StudentQueryRecruitmentsService;
import com.example.jobis.domain.recruitment.service.TeacherQueryRecruitmentsService;
import com.example.jobis.domain.recruitment.service.UpdateRecruitAreaService;
import com.example.jobis.domain.recruitment.service.UpdateRecruitmentService;
import com.example.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
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

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitments")
public class RecruitController {

    private final ApplyRecruitmentService applyRecruitmentService;
    private final UpdateRecruitmentService updateRecruitmentService;
    private final UpdateRecruitAreaService updateRecruitAreaService;
    private final CreateRecruitAreaService createRecruitAreaService;
    private final StudentQueryRecruitmentsService studentQueryRecruitmentsService;
    private final TeacherQueryRecruitmentsService teacherQueryRecruitmentsService;
    private final ChangeRecruitmentStatusService changeRecruitmentStatusService;
    private final StudentQueryRecruitmentDetailService studentQueryRecruitmentDetailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void updateRecruitment(
            @RequestBody @Valid UpdateRecruitmentRequest request,
            @PathVariable("recruitment-id") UUID recruitmentId
    ) {
        updateRecruitmentService.execute(request, recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(
            @RequestBody @Valid UpdateRecruitAreaRequest request,
            @PathVariable("recruit-area-id") UUID recruitAreaId
    ) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}/area")
    public void createRecruitArea(
            @RequestBody @Valid CreateRecruitAreaRequest request,
            @PathVariable("recruitment-id") UUID recruitmentId
    ) {
        createRecruitAreaService.execute(request, recruitmentId);
    }

    @GetMapping("/student")
    public StudentQueryRecruitmentsResponse studentQueryRecruitments(
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        return studentQueryRecruitmentsService.execute(companyName, page);
    }

    @GetMapping("/teacher")
    public TeacherQueryRecruitmentsResponse queryRecruitmentList(
            @RequestParam(value = "company-name", required = false) String companyName,
            @RequestParam(value = "start", required = false) LocalDate start,
            @RequestParam(value = "end", required = false) LocalDate end,
            @RequestParam(value = "status", required = false) RecruitStatus status,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        return teacherQueryRecruitmentsService.execute(companyName, start, end, year, status, page);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}/status")
    public void changeRecruitStatus(
            @PathVariable("recruitment-id") UUID recruitId,
            @RequestParam("status") RecruitStatus status
    ) {
        changeRecruitmentStatusService.execute(recruitId, status);
    }

    @GetMapping("/student/{recruitment-id}")
    public StudentRecruitDetailsResponse studentQueryRecruitmentDetail(
            @PathVariable("recruitment-id") UUID recruitmentId
    ) {
        return studentQueryRecruitmentDetailService.execute(recruitmentId);
    }
}
