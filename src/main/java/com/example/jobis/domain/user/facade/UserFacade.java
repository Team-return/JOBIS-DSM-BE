package com.example.jobis.domain.user.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.domain.repository.StudentRepository;
import com.example.jobis.domain.student.exception.StudentNotFoundException;
import com.example.jobis.domain.teacher.domain.Teacher;
import com.example.jobis.domain.teacher.domain.repository.TeacherRepository;
import com.example.jobis.domain.teacher.exception.TeacherNotFoundException;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.repository.UserJpaRepository;
import com.example.jobis.domain.user.domain.repository.UserRepository;
import com.example.jobis.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final TeacherRepository teacherRepository;

    public User getUser(String accountId) {
        return userRepository.queryUserByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public Student getCurrentStudent() {
        return studentRepository.queryStudentById(
                this.getCurrentUserId()
        ).orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    public Company getCurrentCompany() {
        return companyRepository.queryCompanyById(
                this.getCurrentUserId()
        ).orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public Teacher getCurrentTeacher() {
        return teacherRepository.queryTeacherById(
                this.getCurrentUserId()
        ).orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    public User getCurrentUser() {
        return userRepository.queryUserById(
                this.getCurrentUserId()
        ).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
    private UUID getCurrentUserId() {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
