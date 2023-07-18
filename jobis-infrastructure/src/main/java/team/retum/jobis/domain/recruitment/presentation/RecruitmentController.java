package team.retum.jobis.domain.recruitment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.response.QueryMyRecruitmentResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.service.ApplyRecruitmentService;
import team.retum.jobis.domain.recruitment.service.CreateRecruitAreaService;
import team.retum.jobis.domain.recruitment.service.DeleteRecruitAreaService;
import team.retum.jobis.domain.recruitment.service.DeleteRecruitmentService;
import team.retum.jobis.domain.recruitment.service.QueryMyRecruitmentService;
import team.retum.jobis.domain.recruitment.service.QueryRecruitmentDetailService;
import team.retum.jobis.domain.recruitment.service.StudentQueryRecruitmentsService;
import team.retum.jobis.domain.recruitment.service.TeacherChangeRecruitmentStatusService;
import team.retum.jobis.domain.recruitment.service.TeacherQueryRecruitmentsService;
import team.retum.jobis.domain.recruitment.service.UpdateRecruitAreaService;
import team.retum.jobis.domain.recruitment.service.UpdateRecruitmentService;
import com.example.jobisapplication.common.util.StringUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitments")
public class RecruitmentController {

    private final ApplyRecruitmentService applyRecruitmentService;
    private final UpdateRecruitmentService updateRecruitmentService;
    private final UpdateRecruitAreaService updateRecruitAreaService;
    private final CreateRecruitAreaService createRecruitAreaService;
    private final StudentQueryRecruitmentsService studentQueryRecruitmentsService;
    private final TeacherQueryRecruitmentsService teacherQueryRecruitmentsService;
    private final TeacherChangeRecruitmentStatusService teacherChangeRecruitmentStatusService;
    private final QueryRecruitmentDetailService queryRecruitmentDetailService;
    private final QueryMyRecruitmentService queryMyRecruitmentService;
    private final DeleteRecruitmentService deleteRecruitmentService;
    private final DeleteRecruitAreaService deleteRecruitAreaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void updateRecruitment(
            @RequestBody @Valid UpdateRecruitmentRequest request,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        updateRecruitmentService.execute(request, recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(
            @RequestBody @Valid RecruitAreaRequest request,
            @PathVariable("recruit-area-id") Long recruitAreaId
    ) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}/area")
    public void createRecruitArea(
            @RequestBody @Valid RecruitAreaRequest request,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        createRecruitAreaService.execute(request, recruitmentId);
    }

    @GetMapping("/student")
    public StudentQueryRecruitmentsResponse studentQueryRecruitments(
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "job_code", required = false) Long jobCode,
            @RequestParam(value = "tech_code", required = false) String techCode
    ) {
        List<Long> techCodes = StringUtil.divideString(techCode).stream().map(Long::parseLong).toList();
        return studentQueryRecruitmentsService.execute(companyName, page - 1, jobCode, techCodes);
    }

    @GetMapping("/teacher")
    public TeacherQueryRecruitmentsResponse queryRecruitmentList(
            @RequestParam(value = "company_name", required = false) String companyName,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(value = "status", required = false) RecruitStatus status,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        return teacherQueryRecruitmentsService.execute(companyName, start, end, year, status, page - 1);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/status")
    public void changeRecruitStatus(@RequestBody @Valid ChangeRecruitmentRequest request) {
        teacherChangeRecruitmentStatusService.execute(request);

    }

    @GetMapping("/{recruitment-id}")
    public QueryRecruitmentDetailResponse studentQueryRecruitmentDetail(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        return queryRecruitmentDetailService.execute(recruitmentId);
    }

    @GetMapping("/my")
    public QueryMyRecruitmentResponse queryMyRecruitment() {
        return queryMyRecruitmentService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{recruitment-id}")
    public void deleteRecruitment(@PathVariable("recruitment-id") Long recruitmentId) {
        deleteRecruitmentService.execute(recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/area/{recruit-area-id}")
    public void deleteRecruitArea(@PathVariable("recruit-area-id") Long recruitAreaId) {
        deleteRecruitAreaService.execute(recruitAreaId);
    }
}
