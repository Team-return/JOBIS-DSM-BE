package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.interest.spi.CommandInterestPort;
import team.retum.jobis.domain.interest.spi.QueryInterestPort;
import team.retum.jobis.domain.student.model.Student;

@RequiredArgsConstructor
@UseCase
public class ToggleInterestUseCase {

    private final QueryInterestPort queryInterestPort;
    private final CommandInterestPort commandInterestPort;
    private final QueryCodePort queryCodePort;
    private final SecurityPort securityPort;

    public void execute(Long codeId) {
        Student student = securityPort.getCurrentStudent();
        Code code = queryCodePort.getByIdOrThrow(codeId);

        queryInterestPort.findByStudentIdAndCode(student.getId(), code.getId())
            .ifPresentOrElse(
                commandInterestPort::deleteInterest,
                () -> commandInterestPort.saveInterest(Interest.builder()
                    .studentId(student.getId())
                    .code(code.getId())
                    .build())
            );
    }
}
