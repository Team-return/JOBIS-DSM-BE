package com.example.jobis.domain.teacher.presentaion;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaCodeRequest;
import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.code.service.CreateRecruitAreaCodeService;
import com.example.jobis.domain.code.service.CreateRecruitAreaService;
import com.example.jobis.domain.code.service.DeleteRecruitAreaCodeService;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.service.UpdateRecruitAreaService;
import com.example.jobis.domain.recruit.service.UpdateRecruitmentService;
import com.example.jobis.domain.teacher.presentaion.dto.response.TQueryRecruitmentListResponse;
import com.example.jobis.domain.teacher.service.TeacherQueryRecruitmentListService;
import com.example.jobis.domain.teacher.service.ChangeRecruitmentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final UpdateRecruitmentService updateRecruitmentService;
    private final UpdateRecruitAreaService updateRecruitAreaService;
    private final DeleteRecruitAreaCodeService deleteRecruitAreaCodeService;
    private final CreateRecruitAreaCodeService createRecruitAreaCodeService;
    private final CreateRecruitAreaService createRecruitAreaService;
    private final TeacherQueryRecruitmentListService teacherQueryRecruitmentListService;
    private final ChangeRecruitmentStatusService changeRecruitmentStatusService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruit-id}")
    public void updateRecruitment(@RequestBody @Valid UpdateRecruitmentRequest request, @PathVariable("recruit-id") Long recruitId) {
        updateRecruitmentService.execute(request, recruitId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(@RequestBody @Valid UpdateRecruitAreaRequest request, @PathVariable("recruit-area-id") Long recruitAreaId) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/area/code")
    public void deleteRecruitAreaCode(@RequestParam("recruit-area-id") Long recruitAreaId, @RequestParam("code-id") Long codeId) {
        deleteRecruitAreaCodeService.execute(recruitAreaId, codeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area/code/{recruit-area-id}")
    public void createRecruitAreaCode(@RequestBody @Valid CreateRecruitAreaCodeRequest request, @PathVariable("recruit-area-id") Long recruitAreaId) {
        createRecruitAreaCodeService.execute(recruitAreaId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area/{recruit-id}")
    public void createRecruitArea(@RequestBody @Valid CreateRecruitAreaRequest request, @PathVariable("recruit-id") Long recruitId) {
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
    public void changeRecruitStatus(@PathVariable("recruit-id") Long recruitId, @RequestParam("status")RecruitStatus status) {
        changeRecruitmentStatusService.execute(recruitId, status);
    }

}
