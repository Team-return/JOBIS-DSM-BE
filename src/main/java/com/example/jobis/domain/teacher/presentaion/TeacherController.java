package com.example.jobis.domain.teacher.presentaion;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.service.CreateRecruitAreaService;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.service.UpdateRecruitAreaService;
import com.example.jobis.domain.teacher.presentaion.dto.response.TQueryRecruitmentListResponse;
import com.example.jobis.domain.teacher.service.TeacherQueryRecruitmentListService;
import com.example.jobis.domain.teacher.service.ChangeRecruitmentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {
    private final UpdateRecruitAreaService updateRecruitAreaService;
    private final CreateRecruitAreaService createRecruitAreaService;
    private final TeacherQueryRecruitmentListService teacherQueryRecruitmentListService;
    private final ChangeRecruitmentStatusService changeRecruitmentStatusService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(@RequestBody @Valid UpdateRecruitAreaRequest request, @PathVariable("recruit-area-id") UUID recruitAreaId) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area/{recruit-id}")
    public void createRecruitArea(@RequestBody @Valid CreateRecruitAreaRequest request, @PathVariable("recruit-id") UUID recruitId) {
        createRecruitAreaService.execute(request, recruitId);
    }

    @GetMapping("/recruitment")
    public List<TQueryRecruitmentListResponse> queryRecruitmentList(
            @RequestParam(value = "company-name", required = false) String companyName,
            @RequestParam(value = "start", required = false) LocalDate start,
            @RequestParam(value = "end", required = false) LocalDate end,
            @RequestParam(value = "status", required = false) RecruitStatus status,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "page", defaultValue = "1") Integer page
            ) {
        return teacherQueryRecruitmentListService.execute(companyName, start, end, year, status, page);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/recruitment/{recruit-id}")
    public void changeRecruitStatus(@PathVariable("recruit-id") UUID recruitId, @RequestParam("status")RecruitStatus status) {
        changeRecruitmentStatusService.execute(recruitId, status);
    }

}
