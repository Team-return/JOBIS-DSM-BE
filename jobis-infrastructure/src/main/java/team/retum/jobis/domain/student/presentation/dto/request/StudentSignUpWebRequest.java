package team.retum.jobis.domain.student.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.student.dto.request.StudentSignUpRequest;
import team.retum.jobis.domain.student.model.Gender;
import team.retum.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class StudentSignUpWebRequest {

    @Size(max = 30)
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;

    @NotNull
    private int grade;

    @Size(max = 10)
    @NotBlank
    private String name;

    @NotNull
    private int classRoom;

    @NotNull
    private int number;

    @NotNull
    private Gender gender;

    @Size(max = 300)
    private String profileImageUrl;

    @NotNull
    private PlatformType platformType;

    private String deviceToken;

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
                .platformType(this.platformType)
                .deviceToken(this.deviceToken)
                .build();
    }
}
