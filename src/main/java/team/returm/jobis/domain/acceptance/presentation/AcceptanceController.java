package team.returm.jobis.domain.acceptance.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.acceptance.presentation.dto.request.ChangeContractDateRequest;
import team.returm.jobis.domain.acceptance.presentation.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.returm.jobis.domain.acceptance.service.ChangeContractDateService;
import team.returm.jobis.domain.acceptance.service.TeacherQueryFieldTraineesAndContractWorkersService;
import team.returm.jobis.domain.acceptance.presentation.dto.request.RegisterFieldTraineeRequest;
import team.returm.jobis.domain.acceptance.service.RegisterFieldTraineeService;

@RequiredArgsConstructor
@RequestMapping("/acceptances")
@RestController
public class AcceptanceController {

    private final TeacherQueryFieldTraineesAndContractWorkersService teacherQueryFieldTraineesAndContractWorkersService;
    private final ChangeContractDateService changeContractDateService;
    private final RegisterFieldTraineeService registerFieldTraineeService;

    @GetMapping("/{company-id}")
    public TeacherQueryFieldTraineesAndContractWorkersResponse teacherQueryFieldTraineesAndContractWorkers(
            @PathVariable(name = "company-id") Long companyId
    ) {
        return teacherQueryFieldTraineesAndContractWorkersService.execute(companyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/field-train/{application-id}")
    public void registerFieldTrainee(
            @PathVariable("application-id") Long applicationId,
            @RequestBody @Valid RegisterFieldTraineeRequest request
    ) {
        registerFieldTraineeService.execute(applicationId, request.getStartDate(), request.getEndDate());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/contract-date")
    public void changeWorkContractDate(@RequestBody @Valid ChangeContractDateRequest request) {
        changeContractDateService.execute(request);
    }
}
