package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.student.dto.StudentMyPageResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentMyPageUseCase {

    private final SecurityPort securityPort;

    public StudentMyPageResponse execute() {
        return StudentMyPageResponse.of(securityPort.getCurrentStudent());
    }
}
