package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.PythonPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.spi.CodePort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyInterestRecruitmentUseCase {

    private final PythonPort pythonPort;
    private final SecurityPort securityPort;
    private final CodePort codePort;

    public String execute() {

        Student student = securityPort.getCurrentStudent();

        List<String> major = codePort.getAllByStudentAndInterest(student, CodeType.JOB);
        List<String> tech = codePort.getAllByStudentAndInterest(student, CodeType.TECH);

        return pythonPort.getInterestByPython(major, tech);
    }
}
