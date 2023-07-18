package team.retum.jobis.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.persistence.repository.vo.QueryPassedApplicationStudentsVO;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryPassedApplicationStudentsResponse {

    private final List<QueryPassedApplicationStudentResponse> students;

    public static QueryPassedApplicationStudentResponse of(QueryPassedApplicationStudentsVO vo) {
        return QueryPassedApplicationStudentResponse.builder()
                .applicationId(vo.getApplicationId())
                .studentName(vo.getStudentName())
                .studentGcn(StudentEntity.processGcn(
                        vo.getGrade(),
                        vo.getClassRoom(),
                        vo.getNumber()
                ))
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
