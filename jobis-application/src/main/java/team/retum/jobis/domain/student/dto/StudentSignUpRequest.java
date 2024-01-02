package team.retum.jobis.domain.student.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.student.model.Gender;

@Getter
@Builder
public class StudentSignUpRequest {

    private final String email;

    private final String password;

    private final Integer grade;

    private final String name;

    private final Integer classRoom;

    private final Integer number;

    private final Gender gender;

    private final String profileImageUrl;

    private final PlatformType platformType;
}
