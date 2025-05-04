package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.interest.spi.CommandInterestPort;
import team.retum.jobis.domain.interest.spi.QueryInterestPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
public class ToggleInterestUseCase {

    private final QueryInterestPort queryInterestPort;
    private final CommandInterestPort commandInterestPort;
    private final SecurityPort securityPort;
    private final QueryCodePort queryCodePort;

    public void execute(List<Long> codeIds) {

        Student student = securityPort.getCurrentStudent();
        queryCodePort.existsByAllCodeIds(codeIds);

        List<Interest> interests = queryInterestPort.getAllByStudentIdAndCodes(student.getId(), codeIds);

        Set<Long> codeIdList = interests.stream()
            .map(Interest::getCode)
            .collect(Collectors.toSet());

        codeIds
            .forEach(codeId -> {
                if (codeIdList.contains(codeId)) {
                    interests.stream()
                        .filter(interest -> interest.getCode().equals(codeId))
                        .findFirst()
                        .ifPresent(commandInterestPort::delete);
                } else {
                    commandInterestPort.save(
                        Interest.builder()
                            .studentId(student.getId())
                            .code(codeId)
                            .build()
                    );
                }
            });
    }
}
