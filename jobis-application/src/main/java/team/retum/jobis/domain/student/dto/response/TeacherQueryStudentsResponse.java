package team.retum.jobis.domain.student.dto.response;

import lombok.Builder;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.spi.vo.TeacherStudentsVO;

import java.util.List;

public record TeacherQueryStudentsResponse(
    List<TeacherStudentResponse> students
) {
    @Builder
    public record TeacherStudentResponse(
        Long id,
        String name,
        String studentGcn
    ) {
        public static TeacherStudentResponse from(TeacherStudentsVO vo) {
            return TeacherStudentResponse.builder()
                .id(vo.getId())
                .name(vo.getName())
                .studentGcn(
                    SchoolNumber.processSchoolNumber(
                        SchoolNumber.builder()
                            .grade(vo.getGrade())
                            .classRoom(vo.getClassRoom())
                            .number(vo.getNumber())
                            .build()
                    )
                )
                .build();
        }
    }
}
