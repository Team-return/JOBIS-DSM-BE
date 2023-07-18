package team.retum.jobis.domain.student.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.domain.student.persistence.enums.Department;

@Getter
@Builder
public class StudentMyPageResponse {

    private final String studentName;
    private final String studentGcn;
    private final Department department;
    private final String profileImageUrl;

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
                .profileImageUrl(student.getProfileImageUrl())
                .build();
    }

}
