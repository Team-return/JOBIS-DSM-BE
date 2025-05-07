package team.retum.jobis.domain.interest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudentQueryInterestsResponse {

    private final String studentName;
    private final List<InterestResponse> interests;

    public static StudentQueryInterestsResponse of(Student student, List<InterestResponse> interests) {
        return StudentQueryInterestsResponse.builder()
            .studentName(student.getName())
            .interests(interests)
            .build();
    }
}
