package team.retum.jobis.domain.recruitment.presentation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import team.retum.jobis.domain.recruitment.dto.response.ExportRecruitmentHistoryResponse;
import team.retum.jobis.domain.recruitment.dto.response.QueryMyRecruitmentResponse;
import team.retum.jobis.domain.recruitment.dto.response.QueryMyRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitmentCountResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitmentExistsResponse;
import team.retum.jobis.domain.recruitment.dto.response.StudentQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.dto.response.TeacherQueryRecruitmentsResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ApplyRecruitmentWebRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentStatusWebRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.RecruitAreaWebRequest;
import team.retum.jobis.domain.recruitment.presentation.dto.request.UpdateRecruitmentWebRequest;
import team.retum.jobis.domain.recruitment.usecase.ApplyRecruitmentUseCase;
import team.retum.jobis.domain.recruitment.usecase.CheckRecruitmentExistsUseCase;
import team.retum.jobis.domain.recruitment.usecase.CreateRecruitAreaUseCase;
import team.retum.jobis.domain.recruitment.usecase.DeleteRecruitAreaUseCase;
import team.retum.jobis.domain.recruitment.usecase.DeleteRecruitmentUseCase;
import team.retum.jobis.domain.recruitment.usecase.ExportRecruitmentHistoryUseCase;
import team.retum.jobis.domain.recruitment.usecase.QueryMyRecruitmentUseCase;
import team.retum.jobis.domain.recruitment.usecase.QueryMyRecruitmentsUseCase;
import team.retum.jobis.domain.recruitment.usecase.QueryRecruitmentDetailUseCase;
import team.retum.jobis.domain.recruitment.usecase.StudentQueryRecruitmentsUseCase;
import team.retum.jobis.domain.recruitment.usecase.TeacherChangeRecruitmentStatusUseCase;
import team.retum.jobis.domain.recruitment.usecase.TeacherQueryRecruitmentsUseCase;
import team.retum.jobis.domain.recruitment.usecase.UpdateRecruitAreaUseCase;
import team.retum.jobis.domain.recruitment.usecase.UpdateRecruitmentUseCase;
import team.retum.jobis.global.exception.BadRequestException;
import team.retum.jobis.thirdparty.paser.ExcelAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static team.retum.jobis.global.config.cache.CacheName.RECRUITMENT;

@CacheConfig(cacheNames = RECRUITMENT)
@Validated
@RequiredArgsConstructor
@RequestMapping("/recruitments")
@RestController
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
    private final QueryMyRecruitmentsUseCase queryMyRecruitmentsUseCase;
    private final ExportRecruitmentHistoryUseCase exportRecruitmentHistoryUseCase;
    private final CheckRecruitmentExistsUseCase checkRecruitmentExistsUseCase;
    private final ExcelAdapter excelAdapter;

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentWebRequest webRequest) {
        applyRecruitmentUseCase.execute(webRequest.toDomainRequest());
    }

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void updateRecruitment(
        @RequestBody @Valid UpdateRecruitmentWebRequest webRequest,
        @PathVariable("recruitment-id") Long recruitmentId
    ) {
        updateRecruitmentService.execute(webRequest.toDomainRequest(), recruitmentId);
    }

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(
        @RequestBody @Valid RecruitAreaWebRequest webRequest,
        @PathVariable("recruit-area-id") Long recruitAreaId
    ) {
        updateRecruitAreaService.execute(webRequest.toDomainRequest(), recruitAreaId);
    }

    @CacheEvict(allEntries = true)
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
        @RequestParam(value = "tech_code", required = false) String techCodes,
        @RequestParam(value = "job_code", required = false) String jobCode,
        @RequestParam(value = "winter_intern", required = false) Boolean winterIntern,
        @RequestParam(value = "military_support", required = false) Boolean militarySupport
    ) {
        return studentQueryRecruitmentsUseCase.execute(
            companyName,
            page,
            this.parseCodes(jobCode, techCodes),
            winterIntern,
            militarySupport
        );
    }

    @Cacheable
    @GetMapping("/student/count")
    public TotalPageCountResponse studentQueryRecruitmentCount(
        @RequestParam(value = "name", required = false) String companyName,
        @RequestParam(value = "tech_code", required = false) String techCodes,
        @RequestParam(value = "job_code", required = false) String jobCode,
        @RequestParam(value = "winter_intern", required = false) Boolean winterIntern
    ) {
        return studentQueryRecruitmentsUseCase.getTotalPageCount(
            companyName,
            this.parseCodes(jobCode, techCodes),
            winterIntern
        );
    }

    @Cacheable(condition = "#page <= 5")
    @GetMapping("/teacher")
    public TeacherQueryRecruitmentsResponse getRecruitmentList(
        @RequestParam(value = "company_name", required = false) String companyName,
        @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
        @RequestParam(value = "status", required = false) RecruitStatus status,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "page", defaultValue = "1") @Positive Long page,
        @RequestParam(value = "winter_intern", required = false) Boolean winterIntern,
        @RequestParam(value = "military_support", required = false) Boolean militarySupport,
        @RequestParam(value = "job_code", required = false) String jobCode,
        @RequestParam(value = "tech_code", required = false) String techCodes
    ) {
        return teacherQueryRecruitmentsUseCase.execute(companyName, start, end, year, status, page, winterIntern, militarySupport, this.parseCodes(jobCode, techCodes));
    }

    @GetMapping("/teacher/no-page")
    public TeacherQueryRecruitmentsResponse getRecruitmentListWithoutPage(
        @RequestParam(value = "year") int year,
        @RequestParam(value = "job_code", required = false) String jobCode,
        @RequestParam(value = "tech_code", required = false) String techCodes,
        @RequestParam(value = "winter_intern", required = false) Boolean winterIntern,
        @RequestParam(value = "military_support", required = false) Boolean militarySupport
    ) {
        return teacherQueryRecruitmentsUseCase.executeWithoutPage(year, this.parseCodes(jobCode, techCodes), winterIntern, militarySupport);
    }

    @Cacheable
    @GetMapping("/count")
    public RecruitmentCountResponse countRecruitments(
        @RequestParam(value = "company_name", required = false) String companyName,
        @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
        @RequestParam(value = "status", required = false) RecruitStatus status,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "winter_intern", required = false) Boolean winterIntern,
        @RequestParam(value = "military_support", required = false) Boolean militarySupport,
        @RequestParam(value = "job_code", required = false) String jobCode,
        @RequestParam(value = "tech_code", required = false) String techCodes) {
        return teacherQueryRecruitmentsUseCase.countRecruitments(companyName, start, end, year, status, winterIntern, militarySupport, this.parseCodes(jobCode, techCodes));
    }

    @Cacheable
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

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/status")
    public void changeRecruitStatus(@RequestBody @Valid ChangeRecruitmentStatusWebRequest webRequest) {
        teacherChangeRecruitmentStatusService.execute(webRequest.getRecruitmentIds(), webRequest.getStatus());

    }

    @GetMapping("/{recruitment-id}")
    public QueryRecruitmentDetailResponse queryRecruitmentDetail(
        @PathVariable("recruitment-id") Long recruitmentId
    ) {
        return queryRecruitmentDetailUseCase.execute(recruitmentId);
    }

    @GetMapping("/my/recent")
    public QueryMyRecruitmentResponse queryMyRecruitment() {
        return queryMyRecruitmentUseCase.execute();
    }

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{recruitment-id}")
    public void deleteRecruitment(@PathVariable("recruitment-id") Long recruitmentId) {
        deleteRecruitmentUseCase.execute(recruitmentId);
    }

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/area/{recruit-area-id}")
    public void deleteRecruitArea(@PathVariable("recruit-area-id") Long recruitAreaId) {
        deleteRecruitAreaUseCase.execute(recruitAreaId);
    }

    @GetMapping("/my")
    public QueryMyRecruitmentsResponse getAllMyRecruitments() {
        return queryMyRecruitmentsUseCase.execute();
    }

    @GetMapping("/file")
    public byte[] exportRecruitmentHistory(HttpServletResponse httpResponse) {
        ExportRecruitmentHistoryResponse response = exportRecruitmentHistoryUseCase.execute();
        excelAdapter.setExcelContentDisposition(httpResponse, response.getFileName());
        return response.getFile();
    }

    @GetMapping("/exists")
    public RecruitmentExistsResponse checkRecruitmentExists(@RequestParam(value = "winter_intern") boolean winterIntern) {
        boolean exists = checkRecruitmentExistsUseCase.execute(winterIntern);
        return new RecruitmentExistsResponse(exists);
    }

    private List<Long> parseCodes(String jobCode, String techCodes) {
        List<String> codes = new ArrayList<>(StringUtil.divideString(techCodes, ","));
        if (jobCode != null && !jobCode.isBlank()) {
            codes.add(jobCode);
        }

        try {
            return codes.stream()
                .map(Long::parseLong)
                .toList();
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
