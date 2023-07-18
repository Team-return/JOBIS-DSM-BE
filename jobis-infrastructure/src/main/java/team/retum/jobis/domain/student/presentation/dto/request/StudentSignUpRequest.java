package team.retum.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.persistence.enums.Gender;
import team.retum.jobis.global.util.RegexProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class StudentSignUpRequest {

    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;

    @NotNull
    private Integer grade;

    @NotBlank
    private String name;

    @NotNull
    private Integer classRoom;

    @NotNull
    private Integer number;

    @NotNull
    private Gender gender;

    private String profileImageUrl;
}
