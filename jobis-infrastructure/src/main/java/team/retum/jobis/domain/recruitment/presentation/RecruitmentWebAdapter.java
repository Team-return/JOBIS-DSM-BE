package team.retum.jobis.domain.recruitment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.StringUtil;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.TeacherQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentWebRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentStatusWebRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.RecruitAreaWebRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentWebRequest;
import team.retum.jobis.domain.recruitment.usecase.ApplyRecruitmentUseCase;
import team.retum.jobis.domain.recruitment.usecase.CreateRecruitAreaUseCase;
import team.retum.jobis.domain.recruitment.usecase.DeleteRecruitAreaUseCase;
import team.retum.jobis.domain.recruitment.usecase.DeleteRecruitmentUseCase;
import team.retum.jobis.domain.recruitment.usecase.QueryMyRecruitmentUseCase;
import team.retum.jobis.domain.recruitment.usecase.QueryRecruitmentDetailUseCase;
import team.retum.jobis.domain.recruitment.usecase.StudentQueryRecruitmentsUseCase;
import team.retum.jobis.domain.recruitment.usecase.TeacherChangeRecruitmentStatusUseCase;
import team.retum.jobis.domain.recruitment.usecase.TeacherQueryRecruitmentsUseCase;
import team.retum.jobis.domain.recruitment.usecase.UpdateRecruitAreaUseCase;
import team.retum.jobis.domain.recruitment.usecase.UpdateRecruitmentUseCase;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/recruitments")
public class RecruitmentWebAdapter {

    private final ApplyRecruitmentUseCase applyRecruitmentUseCase;
    private final UpdateRecruitmentUseCase updateRecruitmentService;
    private final UpdateRecruitAreaUseCase updateRecruitAreaService;
    private final CreateRecruitAreaUseCase createRecruitAreaUseCase;
    private final StudentQueryRecruitmentsUseCase studentQueryRecruitmentsUseCase;
    private final TeacherQueryRecruitmentsUseCase teacherQueryRecruitmentsUseCase;
    private final TeacherChangeRecruitmentStatusUseCase teacherChangeRecruitmentStatusService;
    private final QueryRecruitmentDetailUseCase queryRecruitmentDetailUseCase;
    private final QueryMyRecruitmentUseCase queryMyRecruitmentUseCase;
    private final DeleteRecruitmentUseCase deleteRecruitmentUseCase;
    private final DeleteRecruitAreaUseCase deleteRecruitAreaUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentWebRequest webRequest) {
        applyRecruitmentUseCase.execute(webRequest.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void updateRecruitment(
            @RequestBody @Valid UpdateRecruitmentWebRequest webRequest,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        updateRecruitmentService.execute(webRequest.toDomainRequest(), recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(
            @RequestBody @Valid RecruitAreaWebRequest webRequest,
            @PathVariable("recruit-area-id") Long recruitAreaId
    ) {
        updateRecruitAreaService.execute(webRequest.toDomainRequest(), recruitAreaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}/area")
    public void createRecruitArea(
            @RequestBody @Valid RecruitAreaWebRequest webRequest,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        createRecruitAreaUseCase.execute(webRequest.toDomainRequest(), recruitmentId);
    }

    @GetMapping("/student")
    public StudentQueryRecruitmentsResponse studentQueryRecruitments(
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Long page,
            @RequestParam(value = "job_code", required = false) Long jobCode,
            @RequestParam(value = "tech_code", required = false) String techCode,
            @RequestParam(value = "winter_intern", required = false) Boolean winterIntern
    ) {
        List<Long> techCodes = StringUtil.divideString(techCode, ",").stream().map(Long::parseLong).toList();
        return studentQueryRecruitmentsUseCase.execute(companyName, page - 1, jobCode, techCodes, winterIntern);
    }

    @GetMapping("/student/count")
    public TotalPageCountResponse studentQueryRecruitmentCount(
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "job_code", required = false) Long jobCode,
            @RequestParam(value = "tech_code", required = false) String techCode,
            @RequestParam(value = "winter_intern", required = false) Boolean winterIntern
    ) {
        List<Long> techCodes = StringUtil.divideString(techCode, ",").stream().map(Long::parseLong).toList();
        return studentQueryRecruitmentsUseCase.getTotalPageCount(companyName, jobCode, techCodes, winterIntern);
    }

    @GetMapping("/teacher")
    public TeacherQueryRecruitmentsResponse queryRecruitmentList(
            @RequestParam(value = "company_name", required = false) String companyName,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(value = "status", required = false) RecruitStatus status,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "page", defaultValue = "1") @Positive Long page,
            @RequestParam(value = "winter_intern", required = false) Boolean winterIntern
    ) {
        return teacherQueryRecruitmentsUseCase.execute(companyName, start, end, year, status, page - 1, winterIntern);
    }

    @GetMapping("/teacher/count")
    public TotalPageCountResponse queryRecruitmentCount(
            @RequestParam(value = "company_name", required = false) String companyName,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(value = "status", required = false) RecruitStatus status,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "winter_intern", required = false) Boolean winterIntern
    ) {
        return teacherQueryRecruitmentsUseCase.getTotalPageCount(companyName, start, end, year, status, winterIntern);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/status")
    public void changeRecruitStatus(@RequestBody @Valid ChangeRecruitmentStatusWebRequest webRequest) {
        teacherChangeRecruitmentStatusService.execute(webRequest.toDomainRequest());

    }

    @GetMapping("/{recruitment-id}")
    public QueryRecruitmentDetailResponse queryRecruitmentDetail(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        return queryRecruitmentDetailUseCase.execute(recruitmentId);
    }

    @GetMapping("/my")
    public QueryRecruitmentDetailResponse queryMyRecruitment() {
        return queryMyRecruitmentUseCase.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{recruitment-id}")
    public void deleteRecruitment(@PathVariable("recruitment-id") Long recruitmentId) {
        deleteRecruitmentUseCase.execute(recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/area/{recruit-area-id}")
    public void deleteRecruitArea(@PathVariable("recruit-area-id") Long recruitAreaId) {
        deleteRecruitAreaUseCase.execute(recruitAreaId);
    }
}
