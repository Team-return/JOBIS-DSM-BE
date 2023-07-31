package team.retum.jobis.domain.student.presentation.dto.request;

import team.retum.jobis.domain.student.dto.StudentSignUpRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.model.Gender;
import team.retum.jobis.global.util.RegexProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class StudentSignUpWebRequest {

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

    public StudentSignUpRequest toDomainRequest() {
        return StudentSignUpRequest.builder()
                .email(this.email)
                .password(this.password)
                .grade(this.grade)
                .name(this.name)
                .classRoom(this.classRoom)
                .number(this.number)
                .gender(this.gender)
                .profileImageUrl(this.profileImageUrl)
                .build();
    }
}
