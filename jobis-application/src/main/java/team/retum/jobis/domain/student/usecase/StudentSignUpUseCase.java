package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryAuthCodePort;
import team.retum.jobis.domain.student.dto.StudentSignUpRequest;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.CommandStudentPort;
import team.retum.jobis.domain.student.spi.CommandVerifiedStudentPort;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;
import team.retum.jobis.domain.user.spi.QueryUserPort;

@RequiredArgsConstructor
@UseCase
public class StudentSignUpUseCase {

    private final SecurityPort securityPort;
    private final QueryUserPort queryUserPort;
    private final QueryAuthCodePort queryAuthCodePort;
    private final QueryStudentPort queryStudentPort;
    private final CommandStudentPort commandStudentPort;
    private final CommandVerifiedStudentPort commandVerifiedStudentPort;
    private final CommandUserPort commandUserPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(StudentSignUpRequest request) {
        if (queryUserPort.existsUserByAccountId(request.getEmail())) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }

        AuthCode authCode = queryAuthCodePort.queryAuthCodeByEmail(request.getEmail());
        authCode.checkIsVerified();

        if (queryStudentPort.existsByGradeAndClassRoomAndNumber(
                request.getGrade(), request.getClassRoom(), request.getNumber())
        ) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }

        User user = commandUserPort.saveUser(
                User.builder()
                        .accountId(request.getEmail())
                        .password(securityPort.encodePassword(request.getPassword()))
                        .authority(Authority.STUDENT)
                        .build()
        );

        Student student = commandStudentPort.saveStudent(
                Student.builder()
                        .id(user.getId())
                        .schoolNumber(
                                SchoolNumber.builder()
                                        .grade(request.getGrade())
                                        .classRoom(request.getClassRoom())
                                        .number(request.getNumber())
                                        .build()
                        )
                        .name(request.getName())
                        .gender(request.getGender())
                        .department(
                                SchoolNumber.getDepartment(
                                        request.getGrade(),
                                        request.getClassRoom()
                                )
                        )
                        .profileImageUrl(request.getProfileImageUrl())
                        .build()
        );

        commandVerifiedStudentPort.deleteVerifiedStudentByGcnAndName(
                SchoolNumber.processSchoolNumber(student.getSchoolNumber()),
                student.getName()
        );

        return jwtPort.generateTokens(user.getId(), user.getAuthority());
    }
}
