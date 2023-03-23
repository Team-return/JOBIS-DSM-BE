package team.returm.jobis.domain.recruitment.presentation;

import java.time.LocalDate;
import javax.validation.Valid;
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
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentRequest;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentRequest;
import team.returm.jobis.domain.recruitment.presentation.dto.request.CreateRecruitAreaRequest;
import team.returm.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitAreaRequest;
import team.returm.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import team.returm.jobis.domain.recruitment.presentation.dto.response.QueryMyRecruitmentResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentQueryRecruitmentsResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.StudentRecruitDetailsResponse;
import team.returm.jobis.domain.recruitment.presentation.dto.response.TeacherQueryRecruitmentsResponse;
import team.returm.jobis.domain.recruitment.service.ApplyRecruitmentService;
import team.returm.jobis.domain.recruitment.service.CreateRecruitAreaService;
import team.returm.jobis.domain.recruitment.service.DeleteRecruitAreaService;
import team.returm.jobis.domain.recruitment.service.DeleteRecruitmentService;
import team.returm.jobis.domain.recruitment.service.QueryMyRecruitmentService;
import team.returm.jobis.domain.recruitment.service.StudentQueryRecruitmentDetailService;
import team.returm.jobis.domain.recruitment.service.StudentQueryRecruitmentsService;
import team.returm.jobis.domain.recruitment.service.TeacherChangeRecruitmentStatusService;
import team.returm.jobis.domain.recruitment.service.TeacherQueryRecruitmentsService;
import team.returm.jobis.domain.recruitment.service.UpdateRecruitAreaService;
import team.returm.jobis.domain.recruitment.service.UpdateRecruitmentService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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
    private final TeacherChangeRecruitmentStatusService teacherChangeRecruitmentStatusService;
    private final StudentQueryRecruitmentDetailService studentQueryRecruitmentDetailService;
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
            @RequestBody @Valid UpdateRecruitAreaRequest request,
            @PathVariable("recruit-area-id") Long recruitAreaId
    ) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}/area")
    public void createRecruitArea(
            @RequestBody @Valid CreateRecruitAreaRequest request,
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        createRecruitAreaService.execute(request, recruitmentId);
    }

    @GetMapping("/student")
    public StudentQueryRecruitmentsResponse studentQueryRecruitments(
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "keyword", required = false) List<String> keywords
            ) {
        return studentQueryRecruitmentsService.execute(companyName, page, keywords);
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
    @PatchMapping("/status")
    public void changeRecruitStatus(@RequestBody @Valid ChangeRecruitmentRequest request) {
        teacherChangeRecruitmentStatusService.execute(request);

    }

    @GetMapping("/student/{recruitment-id}")
    public StudentRecruitDetailsResponse studentQueryRecruitmentDetail(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        return studentQueryRecruitmentDetailService.execute(recruitmentId);
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
