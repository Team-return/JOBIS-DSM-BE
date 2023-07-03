package team.retum.jobis.domain.acceptance.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.acceptance.presentation.dto.request.CancelFieldTraineesRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.request.ChangeContractDateRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterEmploymentContractRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterFieldTraineeRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.retum.jobis.domain.acceptance.service.CancelFieldTraineesService;
import team.retum.jobis.domain.acceptance.service.ChangeContractDateService;
import team.retum.jobis.domain.acceptance.service.RegisterEmploymentContractService;
import team.retum.jobis.domain.acceptance.service.RegisterFieldTraineeService;
import team.retum.jobis.domain.acceptance.service.TeacherQueryFieldTraineesAndContractWorkersService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/acceptances")
@RestController
public class AcceptanceController {

    private final TeacherQueryFieldTraineesAndContractWorkersService teacherQueryFieldTraineesAndContractWorkersService;
    private final ChangeContractDateService changeContractDateService;
    private final RegisterFieldTraineeService registerFieldTraineeService;
    private final RegisterEmploymentContractService registerEmploymentContractService;
    private final CancelFieldTraineesService cancelFieldTraineesService;

    @GetMapping("/{company-id}")
    public TeacherQueryFieldTraineesAndContractWorkersResponse teacherQueryFieldTraineesAndContractWorkers(
            @PathVariable(name = "company-id") Long companyId
    ) {
        return teacherQueryFieldTraineesAndContractWorkersService.execute(companyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/field-train")
    public void registerFieldTrainee(
            @RequestBody @Valid RegisterFieldTraineeRequest request
    ) {
        registerFieldTraineeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/contract-date")
    public void changeWorkContractDate(@RequestBody @Valid ChangeContractDateRequest request) {
        changeContractDateService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employment")
    public void registerEmploymentContract(@RequestBody @Valid RegisterEmploymentContractRequest request) {
        registerEmploymentContractService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void cancelFieldTrainees(@RequestBody @Valid CancelFieldTraineesRequest request) {
        cancelFieldTraineesService.execute(request);
    }
}