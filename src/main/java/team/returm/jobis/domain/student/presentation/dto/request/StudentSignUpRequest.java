package team.returm.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.student.domain.types.Gender;
import team.returm.jobis.global.util.RegexProperty;

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
}
