package team.retum.jobis.domain.acceptance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.acceptance.spi.vo.AcceptanceVO;
import team.retum.jobis.domain.application.spi.vo.FieldTraineesVO;
import team.retum.jobis.domain.student.model.SchoolNumber;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryFieldTraineesAndContractWorkersResponse {

    private final List<TeacherQueryFieldTraineesResponse> fieldTraineesResponse;
    private final List<TeacherQueryContractWorkersResponse> acceptancesResponse;

    @Getter
    @Builder
    public static class TeacherQueryFieldTraineesResponse {
        private Long applicationId;
        private String studentGcn;
        private String studentName;
        private LocalDate startDate;
        private LocalDate endDate;

        public static List<TeacherQueryFieldTraineesResponse> from(List<FieldTraineesVO> fieldTrainees) {
            return fieldTrainees.stream()
                    .map(
                            fieldTrainee -> TeacherQueryFieldTraineesResponse
                                    .builder()
                                    .applicationId(fieldTrainee.getApplicationId())
                                    .studentGcn(
                                            SchoolNumber.processSchoolNumber(
                                                    SchoolNumber.builder()
                                                            .grade(fieldTrainee.getGrade())
                                                            .classRoom(fieldTrainee.getClassRoom())
                                                            .number(fieldTrainee.getNumber())
                                                            .build()
                                            )
                                    )
                                    .studentName(fieldTrainee.getStudentName())
                                    .startDate(fieldTrainee.getStartDate())
                                    .endDate(fieldTrainee.getEndDate())
                                    .build()
                    ).toList();
        }
    }

    @Getter
    @Builder
    public static class TeacherQueryContractWorkersResponse {
        private Long acceptanceId;
        private String studentGcn;
        private String studentName;
        private LocalDate contractDate;

        public static List<TeacherQueryContractWorkersResponse> from(List<AcceptanceVO> acceptances) {
            return acceptances.stream()
                    .map(
                            acceptance -> TeacherQueryContractWorkersResponse
                                    .builder()
                                    .acceptanceId(acceptance.getAcceptanceId())
                                    .studentGcn(
                                            SchoolNumber.processSchoolNumber(
                                                    SchoolNumber.builder()
                                                            .grade(acceptance.getGrade())
                                                            .classRoom(acceptance.getClassRoom())
                                                            .number(acceptance.getNumber())
                                                            .build()
                                            )
                                    )
                                    .studentName(acceptance.getStudentName())
                                    .contractDate(acceptance.getContractDate())
                                    .build()
                    ).toList();
        }
    }
}
