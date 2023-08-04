package team.retum.jobis.domain.student.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.student.model.Gender;

@Getter
@Builder
public class StudentSignUpRequest {

    private String email;

    private String password;

    private Integer grade;

    private String name;

    private Integer classRoom;

    private Integer number;

    private Gender gender;

    private String profileImageUrl;
}
