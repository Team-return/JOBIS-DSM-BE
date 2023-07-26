package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.model.AuthCode;
import com.example.jobisapplication.domain.auth.spi.JwtPort;
import com.example.jobisapplication.domain.auth.spi.QueryAuthCodePort;
import com.example.jobisapplication.domain.student.dto.StudentSignUpRequest;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.CommandStudentPort;
import com.example.jobisapplication.domain.student.spi.CommandVerifiedStudentPort;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import com.example.jobisapplication.domain.user.model.User;
import com.example.jobisapplication.domain.user.spi.CommandUserPort;
import com.example.jobisapplication.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.student.exception.StudentAlreadyExistsException;
import com.example.jobisapplication.domain.auth.model.Authority;

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

        User userEntity = commandUserPort.saveUser(
                User.builder()
                        .accountId(request.getEmail())
                        .password(securityPort.encodePassword(request.getPassword()))
                        .authority(Authority.STUDENT)
                        .build()
        );

        Student studentEntity = commandStudentPort.saveStudent(
                Student.builder()
                        .id(userEntity.getId())
                        .classRoom(request.getClassRoom())
                        .number(request.getNumber())
                        .name(request.getName())
                        .gender(request.getGender())
                        .grade(request.getGrade())
                        .department(
                                Student.getDepartment(
                                        request.getGrade(),
                                        request.getClassRoom()
                                )
                        )
                        .profileImageUrl(request.getProfileImageUrl())
                        .build()
        );

        commandVerifiedStudentPort.deleteVerifiedStudentByGcnAndName(
                Student.processGcn(
                        studentEntity.getGrade(),
                        studentEntity.getClassRoom(),
                        studentEntity.getNumber()
                ),
                studentEntity.getName()
        );

        return jwtPort.generateTokens(userEntity.getId(), userEntity.getAuthority());
    }
}
