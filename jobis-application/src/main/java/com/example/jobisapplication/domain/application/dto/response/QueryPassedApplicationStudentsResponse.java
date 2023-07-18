package com.example.jobisapplication.domain.application.dto.response;

import com.example.jobisapplication.domain.application.spi.vo.PassedApplicationStudentsVO;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryPassedApplicationStudentsResponse {

    private final List<QueryPassedApplicationStudentResponse> students;

    public static QueryPassedApplicationStudentResponse of(PassedApplicationStudentsVO vo) {
        return QueryPassedApplicationStudentResponse.builder()
                .applicationId(vo.getApplicationId())
                .studentName(vo.getStudentName())
                .studentGcn(Student.processGcn(
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
