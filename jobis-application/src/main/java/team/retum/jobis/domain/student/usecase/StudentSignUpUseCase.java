package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryAuthCodePort;
import team.retum.jobis.domain.student.dto.request.StudentSignUpRequest;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.CommandVerifiedStudentPort;
import team.retum.jobis.domain.student.spi.StudentPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.UserPort;

@RequiredArgsConstructor
@UseCase
public class StudentSignUpUseCase {

    private final UserPort userPort;
    private final SecurityPort securityPort;
    private final QueryAuthCodePort queryAuthCodePort;
    private final StudentPort studentPort;
    private final CommandVerifiedStudentPort commandVerifiedStudentPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(StudentSignUpRequest request) {
        if (userPort.existsByAccountId(request.email())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }

        AuthCode authCode = queryAuthCodePort.queryAuthCodeByEmail(request.email());
        authCode.checkIsVerified();

        SchoolNumber schoolNumber = SchoolNumber.builder()
            .grade(request.grade())
            .classRoom(request.classRoom())
            .number(request.number())
            .build();
        checkStudentExists(schoolNumber, Student.getEntranceYear(request.grade()));

        User user = userPort.save(
            User.builder()
                .accountId(request.email())
                .password(securityPort.encodePassword(request.password()))
                .authority(Authority.STUDENT)
                .token(request.deviceToken())
                .build()
        );

        Student student = studentPort.save(
            Student.builder()
                .id(user.getId())
                .schoolNumber(schoolNumber)
                .name(request.name())
                .gender(request.gender())
                .department(
                    SchoolNumber.getDepartment(
                        request.grade(),
                        request.classRoom()
                    )
                )
                .profileImageUrl(request.profileImageUrl())
                .build()
        );

        commandVerifiedStudentPort.deleteByGcnAndName(
            SchoolNumber.processSchoolNumber(student.getSchoolNumber()),
            student.getName()
        );

        return jwtPort.generateTokens(user.getId(), user.getAuthority(), request.platformType());
    }

    private void checkStudentExists(SchoolNumber schoolNumber, Integer entranceYear) {
        if (studentPort.existsByGradeAndClassRoomAndNumberAndEntranceYear(
            schoolNumber, entranceYear
        )) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
    }
}
