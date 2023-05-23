package team.returm.jobis.domain.student.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.domain.enums.Department;

@Getter
@Builder
public class StudentMyPageResponse {

    private final String studentName;
    private final String studentGcn;
    private final Department department;

    public static StudentMyPageResponse of(Student student) {
        return StudentMyPageResponse.builder()
                .studentName(student.getName())
                .studentGcn(
                        Student.processGcn(
                                student.getGrade(),
                                student.getClassRoom(),
                                student.getNumber()
                        )
                )
                .department(student.getDepartment())
                .build();
    }

}
