package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.spi.vo.PassedApplicationStudentsVO;
import team.retum.jobis.domain.student.model.SchoolNumber;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryPassedApplicationStudentsResponse {

    private final List<QueryPassedApplicationStudentResponse> students;

    public static QueryPassedApplicationStudentResponse of(PassedApplicationStudentsVO vo) {
        return QueryPassedApplicationStudentResponse.builder()
                .applicationId(vo.getApplicationId())
                .studentName(vo.getStudentName())
                .studentGcn(SchoolNumber.processSchoolNumber(
                        SchoolNumber.builder()
                                .grade(vo.getGrade())
                                .classRoom(vo.getClassRoom())
                                .number(vo.getNumber())
                                .build())
                )
                .build();
    }

    @Getter
    @Builder
    public static class QueryPassedApplicationStudentResponse {

        private final Long applicationId;
        private final String studentName;
        private final String studentGcn;
    }
}
