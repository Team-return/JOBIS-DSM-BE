package team.retum.jobis.domain.student.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import com.example.jobisapplication.domain.student.domain.Department;

@Getter
@Builder
public class StudentMyPageResponse {

    private final String studentName;
    private final String studentGcn;
    private final Department department;
    private final String profileImageUrl;

    public static StudentMyPageResponse of(StudentEntity studentEntity) {
        return StudentMyPageResponse.builder()
                .studentName(studentEntity.getName())
                .studentGcn(
                        StudentEntity.processGcn(
                                studentEntity.getGrade(),
                                studentEntity.getClassRoom(),
                                studentEntity.getNumber()
                        )
                )
                .department(studentEntity.getDepartment())
                .profileImageUrl(studentEntity.getProfileImageUrl())
                .build();
    }

}
