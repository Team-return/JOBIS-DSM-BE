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
import team.retum.jobis.domain.acceptance.presentation.dto.request.CancelFieldTraineesWebRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.request.ChangeContractDateWebRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterEmploymentContractWebRequest;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterFieldTraineeWebRequest;
import team.retum.jobis.domain.acceptance.dto.response.TeacherQueryFieldTraineesAndContractWorkersResponse;
import team.retum.jobis.domain.acceptance.usecase.CancelFieldTraineesUseCase;
import team.retum.jobis.domain.acceptance.usecase.ChangeContractDateUseCase;
import team.retum.jobis.domain.acceptance.usecase.RegisterEmploymentContractUseCase;
import team.retum.jobis.domain.acceptance.usecase.RegisterFieldTraineeUseCase;
import team.retum.jobis.domain.acceptance.usecase.TeacherQueryFieldTraineesAndContractWorkersUseCase;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/acceptances")
@RestController
public class AcceptanceWebAdapter {

    private final TeacherQueryFieldTraineesAndContractWorkersUseCase teacherQueryFieldTraineesAndContractWorkersUseCase;
    private final ChangeContractDateUseCase changeContractDateUseCase;
    private final RegisterFieldTraineeUseCase registerFieldTraineeUseCase;
    private final RegisterEmploymentContractUseCase registerEmploymentContractUseCase;
    private final CancelFieldTraineesUseCase cancelFieldTraineesUseCase;

    @GetMapping("/{company-id}")
    public TeacherQueryFieldTraineesAndContractWorkersResponse teacherQueryFieldTraineesAndContractWorkers(
            @PathVariable(name = "company-id") Long companyId
    ) {
        return teacherQueryFieldTraineesAndContractWorkersUseCase.execute(companyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/field-train")
    public void registerFieldTrainee(
            @RequestBody @Valid RegisterFieldTraineeWebRequest request
    ) {
        registerFieldTraineeUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/contract-date")
    public void changeWorkContractDate(@RequestBody @Valid ChangeContractDateWebRequest request) {
        changeContractDateUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employment")
    public void registerEmploymentContract(@RequestBody @Valid RegisterEmploymentContractWebRequest request) {
        registerEmploymentContractUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void cancelFieldTrainees(@RequestBody @Valid CancelFieldTraineesWebRequest request) {
        cancelFieldTraineesUseCase.execute(request.toDomainRequest());
    }
}
