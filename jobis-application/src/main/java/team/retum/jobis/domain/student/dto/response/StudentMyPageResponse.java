package team.retum.jobis.domain.student.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.student.model.Department;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;

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
                SchoolNumber.processSchoolNumber(student.getSchoolNumber())
            )
            .department(student.getDepartment())
            .profileImageUrl(student.getProfileImageUrl())
            .build();
    }

}
